# Clone repository to local
git clone https://github.com/rathanDev/oracle-rest.git

# Navigate to the base directory
cd oracle-rest 

# Install docker or docker desktop 
# Build docker image after making changes 
docker build -t rathandev/oracle-rest .

# Verify docker image
docker images

# Start service
docker run -p 8080:8080 rathandev/oracle-rest

# Check logs
docker logs