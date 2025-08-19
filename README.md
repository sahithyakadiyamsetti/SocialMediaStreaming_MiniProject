Social Media Streaming – Real-Time Trending Words
---Overview

This project demonstrates a real-time social media streaming application using Apache Spark Structured Streaming.
The main goal is to track trending words from live data, similar to analyzing social media posts, and display them with their count and timestamp in real-time.

---Features

Real-time streaming word count from live input

Displays top trending words with their current timestamp

Uses Spark Structured Streaming for efficient real-time processing

Output displayed on the console for easy monitoring

Fully dynamic and scalable

---Technologies Used

Apache Spark 3.x

Scala

Java 8+

---Socket Streaming (localhost)

Project Structure
SocialMediaStream/
├── src/
│   └── main/
│       └── scala/
│           └── SocialMediaStream.scala
├── README.md
└── build.sbt

---How It Works
1. Spark Setup

Initialize a Spark session using:

val spark = SparkSession.builder()
    .appName("SocialMediaStreaming")
    .master("local[*]")
    .getOrCreate()

2. Streaming Source

Read live data from a socket:

val lines = spark.readStream
    .format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .load()

3. Data Processing

Split each line of text into words: flatMap

Rename column value → word

Group by word and count occurrences

Add timestamp using current_timestamp()

Sort by count descending and limit top 7 words

4. Output

Display trending words in the console:

val query = trending.writeStream
    .outputMode("complete")
    .format("console")
    .option("truncate", false)
    .start()


---Output example:
<img width="309" height="181" alt="image" src="https://github.com/user-attachments/assets/9044dd31-e334-4cb1-a6d2-30a531d182a8" />

+-----+-----+-------------------+
|word |count|timestamp          |
+-----+-----+-------------------+
|spark|3    |2025-08-19 22:30:01|
|hello|2    |2025-08-19 22:30:01|
|world|1    |2025-08-19 22:30:01|
+-----+-----+-------------------+

---How to Run

Start a socket server (for testing):

nc -lk 9999   # Linux/Mac

---Run the Scala program:

sbt run


Type messages in the socket server.

--Example:

hello spark hello world spark


Check the console output for trending words and timestamps.
