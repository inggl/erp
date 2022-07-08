FROM gradle:jdk-alpine as build
WORKDIR /workspace/app
COPY --chown=gradle:gradle . /workspace/app
RUN --mount=type=cache,target=/root/.gradle gradle bootJar --no-daemon
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM openjdk:jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.inggl.erp.order.OrderApplication"]