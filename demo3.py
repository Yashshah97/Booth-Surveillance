# -*- coding: utf-8 -*-
"""
Created on Tue Mar 26 12:49:33 2019

@author: Yash Shah
"""

import face_recognition
import urllib.request
import cv2
import os
import time
import numpy as np
from flask import Flask,jsonify,request
import datetime
import shutil
import smtplib 
from email.mime.multipart import MIMEMultipart 
import dlib
from centroidtracker import CentroidTracker
from trackableobject import TrackableObject
from imutils.video import VideoStream
from imutils.video import FPS
import numpy as np
import argparse
import imutils
from email.mime.text import MIMEText 
from email.mime.base import MIMEBase 
from email import encoders
from playsound import playsound
   

app=Flask(__name__)

a = []

@app.route('/',methods=['get'])
def fn1():

    print("in url")
    
    imageName=request.args.get('imageName')
   
    print("imageName=",imageName)
   
    imageSplit=imageName.split(',')
    
    print("imageSplit=",imageSplit)
    
   # print(imageName[0])

    result=fn2(imageSplit)

    print("result=",result)

    response = jsonify(result)

    print("response=", response)

    response.headers.add('Access-Control-Allow-Origin', '*')

    return response

@app.route('/peoplecounter',methods=['get'])
def fn4():

    print("in url")
    
    
    
   # print(imageName[0])

    result=fn5()

    print("result=",result)

    response = jsonify(result)

    print("response=", response)

    response.headers.add('Access-Control-Allow-Origin', '*')

    return response

@app.route('/linecross',methods=['get'])
def fn5():
    ct = CentroidTracker(maxDisappeared=40, maxDistance=50)

    trackers = []
    trackableObjects = {}
    counted = []
    W = None
    H = None
    # construct the argument parse and parse the arguments
    ap = argparse.ArgumentParser()
    ap.add_argument("-i", "--input", default=r"F:\VolumeC\sktop\linecrossmix\IMG4355.mp4",
                    help="path to input video")
    ap.add_argument("-o", "--output",
                    help="path to output video")
    ap.add_argument("-y", "--yolo", default="yolo",
                    help="base path to YOLO directory")
    ap.add_argument("-c", "--confidence", type=float, default=0.3,
                    help="minimum probability to filter weak detections")
    ap.add_argument("-t", "--threshold", type=float, default=0.3,
                    help="threshold when applyong non-maxima suppression")
    args = vars(ap.parse_args())

    # load the COCO class labels our YOLO model was trained on
    labelsPath = os.path.sep.join([args["yolo"], "coco.names"])
    LABELS = open(labelsPath).read().strip().split("\n")
    totalFrames = 0
    # initialize a list of colors to represent each possible class label
    np.random.seed(42)
    COLORS = np.random.randint(0, 255, size=(len(LABELS), 3),
                               dtype="uint8")

    # derive the paths to the YOLO weights and model configuration
    weightsPath = os.path.sep.join([args["yolo"], "yolov3.weights"])
    configPath = os.path.sep.join([args["yolo"], "yolov3.cfg"])

    # load our YOLO object detector trained on COCO dataset (80 classes)
    # and determine only the *output* layer names that we need from YOLO
    print("[INFO] loading YOLO from disk...")
    net = cv2.dnn.readNetFromDarknet(configPath, weightsPath)
    ln = net.getLayerNames()
    ln = [ln[i[0] - 1] for i in net.getUnconnectedOutLayers()]

    # initialize the video stream, pointer to output video file, and
    # frame dimensions
    vs = cv2.VideoCapture(args["input"])
    
    writer = None
    # (W, H) = (None, None)
    totalFrames = 0
    totalDown = 0
    totalUp = 0

    # try to determine the total number of frames in the video file
    try:
        prop = cv2.cv.CV_CAP_PROP_FRAME_COUNT if imutils.is_cv2() \
            else cv2.CAP_PROP_FRAME_COUNT
        total = int(vs.get(prop))
        print("[INFO] {} total frames in video".format(total))

    # an error occurred while trying to determine the total
    # number of frames in the video file
    except:
        print("[INFO] could not determine # of frames in video")
        print("[INFO] no approx. completion time can be provided")
        total = -1
    counterRight = 0
    counterLeft = 0
    # loop over frames from the video file stream
    classIDs = []
    while True:
        grabbed, frame = vs.read()
    

        if not grabbed:
            break
        status = "Waiting"
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        if W is None or H is None:
            (H, W) = frame.shape[:2]

        frame = imutils.resize(frame, W, H)
        boxes = []
        confidences = []

        dsa = []
        rects = []
        if totalFrames %50 == 0:
            status = "detecting"

            trackers = []
            blob = cv2.dnn.blobFromImage(frame, 1 / 255.0, (416, 416), swapRB=True, crop=False)
            net.setInput(blob)

            layerOutputs = net.forward(ln)

            for output in layerOutputs:
                for detection in output:
                    scores = detection[5:]
                    classID = np.argmax(scores)
                    confidence = scores[classID]

                    if LABELS[classID]!="person" :
                        continue
                    if confidence > args["confidence"]:

                        box = detection[0:4] * np.array([W, H, W, H])

                        (centerX, centerY, width, height) = box.astype("int")
                        x = int(centerX - (width / 2))
                        y = int(centerY - (height / 2))
                        tracker = dlib.correlation_tracker()
                        rect = dlib.rectangle(int(x), int(y), int(x + width), int(y + height))
                        tracker.start_track(rgb, rect)
                        trackers.append(tracker)
                        classIDs.append(classID)



        else:

            for tracker in trackers:
                status = "Tracking"

                tracker.update(rgb)
                pos = tracker.get_position()

                startX = int(pos.left())
                startY = int(pos.top())
                endX = int(pos.right())
                endY = int(pos.bottom())

                rects.append((startX, startY, endX, endY))

        cv2.line(frame, (W//2, 0), (W//2, H), (0, 255, 255), 2)

        objects = ct.update(rects)
        d=-1
        for (objectID, centroid) in objects.items():
            d+=1
            to = trackableObjects.get(objectID, None)
            if to is None:
                to = TrackableObject(objectID, centroid)

            else:
                y = [c[1] for c in to.centroids]
                direction = centroid[1] - np.mean(y)
                to.centroids.append(centroid)

                if not to.counted or to.counted == "gngup" or to.counted == "gngdown" or to.counted == "not known" or to.counted=="down" or to.counted=="up":
                    print(to.counted)
                    if centroid[0] < W // 2:

                        if to.counted == "gngdown":
                            totalUp += 1
                            cv2.imwrite(r"C:\Users\Yash Shah\Desktop\linecrossmix\photos\up\{}.jpg".format(str(len(os.listdir(r"C:\Users\Yash Shah\Desktop\linecrossmix\photos\up"))+1)),frame)
                            to.counted = "down"
                            playsound('sound.mp3')
                        else:
                            to.counted = "gngup"

                    elif centroid[0] > W //2:
                        
                        if to.counted == "gngup":
                            totalDown += 1
                            cv2.imwrite(r"C:\Users\Yash Shah\Desktop\linecrossmix\photos\down\{}.jpg".format(str(len(os.listdir(r"C:\Users\Yash Shah\Desktop\linecrossmix\photos\down"))+1)),frame)

                            to.counted = "up"
                            playsound('sound.mp3')
                        else:
                            to.counted = "gngdown"
            trackableObjects[objectID] = to

            text = "ID {}".format(objectID)
            # text = "{}".format(LABELS[classIDs[d]])
            cv2.putText(frame, text, (centroid[0] - 10, centroid[1] - 10),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
            cv2.circle(frame, (centroid[0], centroid[1]), 2, (0, 255, 0), -1)

        info = [("up", totalUp), ("down", totalDown), ("Status", status)]

        for (i, (k, v)) in enumerate(info):
            text = "{}: {}".format(k, v)
            cv2.putText(frame, text, (10, H - ((i * 20) + 20)),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.6, (0, 0, 255), 2)

        if writer is not None:
            writer.write(frame)
        frame=cv2.resize(frame,None,fx=0.5,fy=0.5)
        cv2.imshow("Frame", frame)
        key = cv2.waitKey(1) & 0xFF

        if key == ord("q"):
            break

        totalFrames += 1

    if writer is not None:
        writer.release()

    if not args.get("input", False):
        vs.stop()

    else:
        vs.release()

    cv2.destroyAllWindows()
    print("totsl fram======",totalFrames    )
    print("info==================",info)

    infoDict = dict(info)

    return  infoDict


@app.route('/mailShow',methods=['get'])
def fn3(date1):
    print(date1)
    shutil.make_archive((r'E:\PROJECT\BoothSurveillanceSystem\src\main\webapp\document\{x}').format(x=date1), 'zip', r'E:\PROJECT\BoothSurveillanceSystem\src\main\webapp\document\{x}'.format(x=date1)).format(x=date1)
    fromaddr = "transense.28@gmail.com"
    toaddr = "yashshah2791@gmail.com"
       
    # instance of MIMEMultipart 
    msg = MIMEMultipart() 
      
    # storing the senders email address   
    msg['From'] = fromaddr 
      
    # storing the receivers email address  
    msg['To'] = toaddr 
      
    # storing the subject  
    msg['Subject'] = "Check1"
      
    # string to store the body of the mail 
    body = "Check1"
      
    # attach the body with the msg instance 
    msg.attach(MIMEText(body, 'plain')) 
      
    # open the file to be sent  
    filename = "2019-03-19_09-57-17"
    attachment = open((r"E:\PROJECT\BoothSurveillanceSystem\src\main\webapp\document\{}.zip").format(date1), "rb")
      
    # instance of MIMEBase and named as p 
    p = MIMEBase('application', 'zip') 
      
    # To change the payload into encoded form 
    p.set_payload((attachment).read()) 
      
    # encode into base64 
    encoders.encode_base64(p) 
       
    p.add_header('Content-Disposition', "attachment; filename= %s" % filename) 
      
    # attach the instance 'p' to instance 'msg' 
    msg.attach(p) 
      
    # creates SMTP session 
    s = smtplib.SMTP('smtp.gmail.com', 587) 
      
    # start TLS for security 
    s.starttls() 
      
    # Authentication 
    s.login(fromaddr, "Vaibhavi@288") 
      
    # Converts the Multipart msg into a string 
    text = msg.as_string() 
      
    # sending the mail 
    s.sendmail(fromaddr, toaddr, text) 
      
    # terminating the session 
    s.quit() 


@app.route('/showResult',methods=['get'])
def fn2(imageSplit):
    picShow=[]
    print("length=======",len(imageSplit))
    for i in range(0,len(imageSplit)):
        picShow.append(imageSplit[i].split('.jpg'))
    print("imagesplit========",imageSplit)
    print("Picshow========",picShow)
    #print("Picshow========",picShow[0])
    # Get a reference to webcam #0 (the default one)
    url='http://192.168.43.1:8080/shot.jpg'
    video_capture = cv2.VideoCapture(0)
    count=0
    d=0
    
    # Load a sample picture and learn how to recognize it.
#    obama_image = face_recognition.load_image_file("./images/yash2.jpg")
#    obama_face_encoding = face_recognition.face_encodings(obama_image)[0]
    
    # Load a second sample picture and learn how to recognize it.
#    biden_image = face_recognition.load_image_file("./images/shaurya.jpg")
#    biden_face_encoding = face_recognition.face_encodings(biden_image)[0]
#    
#    mark_image = face_recognition.load_image_file("./images/meet.jpg")
#    mark_face_encoding = face_recognition.face_encodings(mark_image)[0]
#    
    barry_image = face_recognition.load_image_file("E:/PROJECT/BoothSurveillanceSystem/src/main/webapp/document/demo/{}".format(imageSplit[0]))
    barry_face_encoding = face_recognition.face_encodings(barry_image)[0]
    
    yash_image = face_recognition.load_image_file("E:/PROJECT/BoothSurveillanceSystem/src/main/webapp/document/demo/{}".format(imageSplit[1]))
    yash_face_encoding = face_recognition.face_encodings(yash_image)[0]
    
    # Create arrays of known face encodings and their names
    known_face_encodings = [
#        obama_face_encoding,
#        biden_face_encoding,
#        mark_face_encoding,
        barry_face_encoding,
        yash_face_encoding
    ]
    known_face_names = [
#        "Yash Shah",
#        "Shaurya 22c",
#        "Meet shah",
         picShow[0][0],
        picShow[1][0]
    ]
    
    # Initialize some variables
    face_locations = []
    face_encodings = []
    face_names = []
    all_time_faces_names = []
    Unknown_array = []
    process_this_frame = True
    #start_time = time.time()
    font = cv2.FONT_HERSHEY_SIMPLEX
    mydir = os.path.join(r'E:\PROJECT\BoothSurveillanceSystem\src\main\webapp\document', datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S'))
    os.makedirs(mydir)
    date1=datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
    while True:
        # Grab a single frame of video
        ret, frame = video_capture.read()
        frame=urllib.request.urlopen(url)
        imgNp=np.array(bytearray(frame.read()),dtype=np.uint8)
        img=cv2.imdecode(imgNp,-1)
        
        #gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        #faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    
        # Resize frame of video to 1/4 size for faster face recognition processing
        small_frame = cv2.resize(img, (0, 0), fx=0.25, fy=0.25)
    
        # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
        rgb_small_frame = small_frame[:, :, ::-1]
    
        # Only process every other frame of video to save time
        if process_this_frame:
            # Find all the faces and face encodings in the current frame of video
            face_locations = face_recognition.face_locations(rgb_small_frame,number_of_times_to_upsample=2)
            face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)
    
            face_names = []
            for face_encoding in face_encodings:
                # See if the face is a match for the known face(s)
                matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
                name = "Unknown"
    
                # If a match was found in known_face_encodings, just use the first one.
                if True in matches:
                    first_match_index = matches.index(True)
                    name = known_face_names[first_match_index]
                # Add all names to array for displaying on the screen
                face_names.append(name)
                # if person is unknown then add that to the unknow array for counting total number of unknown people
                if name == "Unknown":
                    
                    Unknown_array.append(name)
                # else filter duplicate names and add those to the final array
                else:
                    if name not in all_time_faces_names:
                        all_time_faces_names.append(name)
                        print("UNKNOWN")
        process_this_frame = not process_this_frame
    
       
        # Display the results
        for (top, right, bottom, left), name in zip(face_locations, face_names):
            # Scale back up face locations since the frame we detected in was scaled to 1/4 size
            top *= 4
            right *= 4
            bottom *= 4
            left *= 4
    
            # Draw a box around the face
            cv2.rectangle(img, (left+20, top), (right+20, bottom), (0, 0, 255), 2)
           
            if(name=="Unknown"):
               # print(int(time.time() - start_time))
                if(d==0):
                    
                    
                    start_time = time.time()
               # print(int(time.time() - start_time))
                
                #cv2.putText(frame,str(int(time.time() - start_time)),(555,470), font, 1,(255,255,255),2,cv2.LINE_AA)
                if(int(time.time() - start_time)>=10 and int(time.time() - start_time)<=12):
                    #print(int(time.time() - start_time))
                    if mydir:
                        cv2.imwrite(os.path.join(mydir, '%d.png') % count,img )
                        count=count+1
                        cv2.putText(img,str(int(time.time() - start_time)),(555,470), font, 1,(255,255,255),2,cv2.LINE_AA)
                if(int(time.time() - start_time)>14):
                    start_time = time.time()
                    
                d=d+1
               
                
            # Draw a label with a name below the face
            cv2.rectangle(img, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
            font = cv2.FONT_HERSHEY_DUPLEX
            cv2.putText(img, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)
    
        # Display the resulting image
        
        cv2.imshow('Vide3q4wgetsfo', img)
    	
        # Hit 'q' on the keyboard to quit! 
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    
    # Release handle to the webcam
    print(all_time_faces_names)
    

    path, dirs, files = next(os.walk(mydir))
    file_count = len(files)
    print(files)
    
            
    video_capture.release()
    cv2.destroyAllWindows()

    infoDict={"pictures":files,"date":date1}

    print("infoDict=",infoDict)
    
    
    #fn3(date1)

    return infoDict

app.run()

