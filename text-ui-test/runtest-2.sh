#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL-2.TXT" ]
then
    rm ACTUAL-2.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input-2.txt file and redirect the output to the ACTUAL-2.TXT
java -classpath ../bin Duke < input-2.txt > ACTUAL-2.TXT

# convert to UNIX format
cp EXPECTED-2.TXT EXPECTED-2-UNIX.TXT
dos2unix ACTUAL-2.TXT EXPECTED-2-UNIX.TXT

# compare the output to the expected output
diff ACTUAL-2.TXT EXPECTED-2-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi