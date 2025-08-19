Social Media Streaming – Real-Time Trending Words
Overview
This project demonstrates a real-time social media streaming application using Apache Spark Structured Streaming. The main goal is to track trending words from live data, similar to analyzing social media posts, and display them with their count and timestamp in real-time.

Features
Real-time streaming word count from live input.
Displays top trending words with their current timestamp.
Uses Spark Structured Streaming for efficient real-time processing.
Output displayed on the console for easy monitoring.
Fully dynamic and scalable.

Technologies Used
Apache Spark 3.x
Scala
Java 
Socket Streaming (localhost)

SocialMediaStream/
├── src/
│   └── main/
│       └── scala/
│           └── SocialMediaStream.scala
├── README.md
└── build.sbt

How It Works
Spark Setup:
Initialize a Spark session with SparkSession.builder() and set the master to local[*].

Streaming Source:
Read live data from a socket using spark.readStream.format("socket"). The host is localhost and the port is 9999.

Data Processing:
Split each line of text into words (flatMap).
Rename the column value to word.
Group by word and count occurrences.
Add timestamp using current_timestamp() to track when the data was processed.
Sort by count descending and limit to top 7 trending words.

Output:
Display the trending words and their count in the console using writeStream.format("console").
Output mode is set to "complete" to show full updated counts each time new data arrives.

How to Run
Start a socket server (for testing):
nc -lk 9999
Or use telnet localhost 9999 on Windows.

Run the Scala program:
sbt run
Type messages in the socket server. Example
hello spark hello world spark


Console Output:
+-----+-----+-------------------+
|word |count|timestamp          |
+-----+-----+-------------------+
|spark|3    |2025-08-19 22:30:01|
|hello|2    |2025-08-19 22:30:01|
|world|1    |2025-08-19 22:30:01|
+-----+-----+-------------------+
