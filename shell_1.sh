#!/bin/bash
string="runoob is a great site"
echo ${string}
echo ${#string}
array[0]=0
array[1]=1
array[2]=2
array[5]=5
:<<注释
	四道口外婆动物破解动物哦就得破耳机我的
	${array[@]}
注释
echo ${#array[@]}
echo ${array[*]}
echo $#
args=$@
echo args=${args[@]}

declare -A dict=(["apple"]="fruit", ["banana"]="fruit", ["tomato"]="vegetable")
echo ${!dict[*]} 
for value in ${!dict[*]}
do
	echo $value
done

echo `expr 1 + 1 + 1 + 1`

if [ "$string" ]
then
	echo $string
fi

if [ -z "$string" ]
then
	echo "error"
fi

echo -e "test\"\n"

echo `date`

echo 请输入a，b
read a
read b
if ((a>b));then
	printf "a=%d > b=%d" $a $b
elif ((a<b));then
	printf "a=%d < b=%d" $a $b
else
	printf "a=%d = b=%d" $a $b
fi

while :
do
    echo -n "输入 1 到 5 之间的数字: "
    read aNum
    case $aNum in
        1|2|3|4|5) 
	    echo "你输入的数字为 $aNum!"
	    break
	;;
        *) 
	    echo "你输入的数字不是 1 到 5 之间的!"
            continue
            echo "游戏结束"
        ;;
    esac
done

func(){
    sum=0
    for item in $@
    do
	sum=`expr $sum + $item`
    done
    return $sum

}

echo 加法计算一直输入数字，结束输入0
read a
sum[0]=$a
while [ $a != 0 ]
do
    read a
    sum+=($a)
done
echo 你的输入${sum[@]}
func ${sum[@]}
echo $?


