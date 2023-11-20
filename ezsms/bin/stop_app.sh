#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"


cnt=$(ps -ef | grep ezsms.jar | grep -v grep | wc -l) 
if [ $cnt == "0" ]; then
   echo "ezsms already stopped." 
elif [ $cnt -eq 1 ]; then
   echo "stop ezsms. "
   pkill -9 -f ezsms.jar
elif [ $cnt -ge 2 ]; then
   echo "More than 2 process of ezsms is alive, need to check."
fi
