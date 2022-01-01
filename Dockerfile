FROM gradle
COPY .  .
CMD ["./gradlew" , "test"]