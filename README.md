# Kafka-Assignment

#### Produce a user message into a Kafka topic and consume it using the Kafka Producer and Consumer APIs. Once the message is consumed from a consumer, it should be sinked(written) into a file.

#### Message Structure
User message --->{"id":"1","name":"some_name","age":"24","course":"BTech."}

### Version : 1.0

#### Package
1. com.knoldus

#### inside com.knoldus:

1. ProducerConsumer --> Contains two classes --> 1. Producer 2. Consumer
2. SerialzationClasses --> Contains two classes --> 1. StudentDataSerializer 2. StudentDataDeserializer
3. StudentModel --> Contains class --> 1. StudentDetailModel
