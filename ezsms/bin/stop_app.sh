#!/bin/bash

cnt=$(ps -ef | grep ezsms | grep -v grep | wc -l) 
if [ $cnt == "0" ]; then
   echo "ezsms already stopped." 
elif [ $cnt -eq 1 ]; then
   echo "stop ezsms. "
   pkill -9 -f ezsms.jar
elif [ $cnt -ge 2 ]; then
   echo "More than 2 process of ezsms is alive, need to check."
fi
