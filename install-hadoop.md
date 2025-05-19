
# Install Hadoop on Ubuntu

This guide provides step-by-step instructions to install and configure Apache Hadoop on Ubuntu (versions 20.04, 22.04, or 24.04) in a single-node (pseudo-distributed) setup.

## Prerequisites

- A system running Ubuntu 20.04, 22.04, or 24.04
- Sudo or root privileges
- Access to a terminal/command line

## Step 1: Update System Packages

```bash
sudo apt update
```

## Step 2: Install Java Development Kit (JDK)

```bash
sudo apt install openjdk-8-jdk -y
```

Verify the installation:

```bash
java -version
javac -version
```

## Step 3: Create a Hadoop User

```bash
sudo adduser hdoop
```

Switch to the new user:

```bash
su - hdoop
```

## Step 4: Configure SSH for Hadoop User

```bash
sudo apt install openssh-server openssh-client -y
ssh-keygen -t rsa -P ""
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
ssh localhost
```

## Step 5: Download and Install Hadoop

```bash
wget https://downloads.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
tar -xzf hadoop-3.3.6.tar.gz
sudo mv hadoop-3.3.6 /usr/local/hadoop
```

## Step 6: Configure Environment Variables

Edit `.bashrc`:

```bash
nano ~/.bashrc
```

Add the following:

```bash
export HADOOP_HOME=/usr/local/hadoop
export HADOOP_INSTALL=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

Apply changes:

```bash
source ~/.bashrc
```

## Step 7: Configure Hadoop

### 7.1: Edit `hadoop-env.sh`

```bash
nano $HADOOP_HOME/etc/hadoop/hadoop-env.sh
```

Add:

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

### 7.2: Configure `core-site.xml`

```xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

### 7.3: Configure `hdfs-site.xml`

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

### 7.4: Configure `mapred-site.xml`

```bash
cp $HADOOP_HOME/etc/hadoop/mapred-site.xml.template $HADOOP_HOME/etc/hadoop/mapred-site.xml
nano $HADOOP_HOME/etc/hadoop/mapred-site.xml
```

```xml
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```

### 7.5: Configure `yarn-site.xml`

```xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
```

## Step 8: Format the Hadoop Filesystem

```bash
hdfs namenode -format
```

## Step 9: Start Hadoop Services

```bash
start-dfs.sh
start-yarn.sh
jps
```

## Step 10: Access Hadoop Web Interfaces

- NameNode: [http://localhost:9870](http://localhost:9870)
- ResourceManager: [http://localhost:8088](http://localhost:8088)

For more details, visit [How to Install Hadoop on Ubuntu](https://phoenixnap.com/kb/install-hadoop-ubuntu).
