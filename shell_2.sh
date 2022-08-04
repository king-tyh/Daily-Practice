whoami > output.txt 
echo "重定向到了输出文件" > output.txt

#把输出和错误信息都追加输出到 Output2.txt 0:输入 1:输出 2:错误信息
echo "重定向到不存在的文件" `date`  >> output2.txt 2>&1
sleep 10
echo success

