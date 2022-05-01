<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.12.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<groupId>${namespace}</groupId>
	<artifactId>${name}</artifactId>
	<version>0.0.1</version>
	<name>${name}</name>
	<packaging>jar</packaging>

	<description>Demo project for Spring Boot</description>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<profiles>
		<profile>
			<id>dev</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
				<environment>dev</environment>
				<port>8080</port>
			</properties>
		</profile>
		<profile>
			<id>test</id>
			<properties>
				<environment>test</environment>
				<port>8080</port>
			</properties>
		</profile>
		<profile>
			<id>prod</id>
			<properties>
				<environment>prod</environment>
				<port>8080</port>
			</properties>
		</profile>
	</profiles>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<layers>
						<enabled>true</enabled>
					</layers>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>

		<!-- 替换占位符，排除profile无关的配置文件 -->
		<resources>
			<resource>
				<directory>src/main/resources</directory>
				<!-- 设置 filtering=true 后会替换指定文件中的@@占位符，例如@port@ -->
				<filtering>true</filtering>
				<includes>
					<include>**/*</include>
				</includes>
				<excludes>
					<exclude>application-*.yml</exclude>
				</excludes>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
				<filtering>true</filtering>
				<includes>
					<include>application-${r'environment'}.yml</include>
				</includes>
			</resource>
		</resources>

	</build>

	<repositories>
		<repository>
			<id>Huawei Nexus</id>
			<name>Huawei Nexus</name>
			<url>http://192.168.0.37:8081/repository/maven-releases/</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>

	<!-- 配置分发管理：内部私服maven库 -->
	<distributionManagement>
		<repository>
			<id>releases</id>
			<name>Huawei Nexus Release</name>
			<url>http://192.168.0.37:8081/repository/maven-releases/</url>
		</repository>
		<snapshotRepository>
			<id>snapshots</id>
			<name>Huawei Nexus SNAPSHOTS</name>
			<url>http://192.168.0.37:8081/repository/maven-snapshots/</url>
		</snapshotRepository>
	</distributionManagement>



</project>
