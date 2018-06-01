#!/bin/bash
#####################################################Environment Setting#######################################################

#程序代码数组

APPS=(advertising common counterfeit finance inventory ranking order report reviewer yidayun goodreview)

#程序名称数组

NAMES=(ppc模块 公共模块 跟卖模块 财务模块 库存模块 关键字排名模块 订单模块 销售数据模块 评手模块 易达云模块 好评追讨模块)

#jar包数组

JARS=(bison-web-advertising-service.jar bison-web-common-service.jar bison-web-counterfeit-service.jar bison-web-finance-service.jar bison-web-inventory-service.jar bison-web-keyword-ranking-service.jar bison-web-order-service.jar bison-web-report-service.jar bison-web-reviewer-service.jar bison-web-yidayun-service.jar bison-web-review-good-task-service.jar)

#jar包路径数组

PATHS=(/application/provider /application/provider /application/provider /application/provider /application/provider /application/provider /application/provider /application/provider  /application/provider /application/provider /application/provider)

start(){
local APPNAME=
local NAME=
local CLASSNAME=
local PROJECTDIR=
local command="sh service.sh start"
local cmd2="$1"
local cmd2ok=0
local cnt=0
local okcnt=0
#local C_PID="0"
#for i in `seq 0 22`
echo "---------------------------开始启动服务..."
for(( i=0;i<${#APPS[@]};i++))
do
APPNAME=${APPS[$i]}
NAME=${NAMES[$i]}
CLASSNAME=${JARS[$i]}
PROJECTDIR=${PATHS[$i]}
if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
cmd2ok=1
C_PID="0"
cnt=0
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`	
#do 
#C_PID=$(ps --no-heading $pid | wc -l)
#if [ "$C_PID" == "1" ]; then
if [ -n "$PID" ]
then
echo "$APPNAME---$NAME:己经运行,PID=$PID"
#okcnt=$(($okcnt+1))
else
cd $PROJECTDIR
rm -f $PROJECTDIR/nohup.out
command="nohup java -jar $CLASSNAME"
exec $command >> $PROJECTDIR/nohup.out &
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#do
# C_PID=$(ps --no-heading $pid | wc -l)
#done
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`	
cnt=0
#while (("$C_PID" == "0"))
while [ -z "$PID" ]
do
if (($cnt==30))
then
echo "$APPNAME---$NAME:$cnt秒内未启动，请检查！"
break
fi
cnt=$(($cnt+1))
sleep 1s
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#do
# C_PID=$(ps --no-heading $pid | wc -l)
#done
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`	
done
okcnt=$(($okcnt+1))
echo "$APPNAME---$NAME:己经成功启动,PID=$PID"
fi 
#done	
fi
done
if (($cmd2ok==0))
then
echo "command2: all|advertising|common|counterfeit|finance|inventory|ranking|order|report|reviewer|yidayun|goodreview"
else
echo "---------------------------本次启动:$okcnt个服务"
fi
}

stop(){ 
local APPNAME=
local CLASSNAME=
local PROJECTDIR=
local command="sh service.sh stop"
local cmd2="$1"
local cmd2ok=0
#local C_PID="0"
local okcnt=0
echo "---------------------------开始停止服务..."
for(( i=0;i<${#APPS[@]};i++))
do
APPNAME=${APPS[$i]}
NAME=${NAMES[$i]}
CLASSNAME=${JARS[$i]}
PROJECTDIR=${PATHS[$i]}
if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
cmd2ok=1
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read PID 
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`	
#do 
#C_PID=$(ps --no-heading $PID | wc -l)
#if [ "$C_PID" == "1" ]; then
if [ -n "$PID" ]
then
echo "$NAME:PID=$PID准备结束"
kill $PID
#C_PID=$(ps --no-heading $PID | wc -l)
#while (("$C_PID" == "1"))
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
while [ -n "$PID" ]
do
sleep 1s
#C_PID=$(ps --no-heading $PID | wc -l)
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
done
echo "$NAME:成功结束"
okcnt=$(($okcnt+1))
else
echo "$NAME:未运行"
fi 
#done 
fi
done
if (($cmd2ok==0))
then
echo "command2: all|advertising|common|counterfeit|finance|inventory|ranking|order|report|reviewer|yidayun|goodreview"
else
echo "---------------------------本次共停止:$okcnt个服务" 
fi
}

case "$1" in 
start) 
start "$2"
exit 1
;; 
stop) 
stop "$2"
;; 
restart) 
stop "$2"
start "$2"
;; 
*) 
echo "command1: start|stop|restart" 
exit 1 
;; 
esac
