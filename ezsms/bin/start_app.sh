#!/bin/bash

RUN_MODE="bg" # choose fg or bg
### RUN_MODE에 따른 logback 설정 ###
# bg : <appender-ref ref="f"/> 로 설정.
# fg : <appender-ref ref="c"/> 로 설정.
# logback은 수정 후 적용하려면 그래들 빌드해야 한다..
# vscode 에서 실행할땐 그래들 빌드 필요없음.

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

cnt=$(ps -ef | grep ezsms.jar | grep -v grep | wc -l) 
if [ $cnt == "0" ]; then
    export JAVA_OPTS="-Xms4g -Xmx4g -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -Djava.io.tmpdir=tmp"
    if [ $RUN_MODE == "bg" ]; then 
        nohup java -jar ezsms.jar &
        echo "starting application ezsms in backGround"
        sleep 0.5
        rm nohup.out
        echo "removing nohup.out . . ."
    elif [ $RUN_MODE == "fg" ]; then
        echo "starting application ezsms in foreGround"
        java -jar ezsms.jar 
    else
        echo "unknown run mode.. check again."
    fi
else
   echo "already running.."
fi
