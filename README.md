# GitRepoInspector

A brief description of what this project does and who it's for.

## Installation

### Clone the repository:

git clone https://github.com/your-username/your-repository.git

### Build the project:

./gradlew build

### Usage

Run the project:

Insert your github personal access tokens in `aplication.properties` in a field `git.token` to use it in the project api calls 

./gradlew run

Open your web browser and navigate to http://localhost:8080

# API

### GET /repositories/{username}

Returns a list of the user's GitHub repositories that are not forks.

### Request

username (required): The GitHub username of the user whose repositories to retrieve.

### Response

##### 200 OK: Returns a JSON array of GitRepositoryInfo objects, each containing the following fields:

    repositoryName: The name of the repository.
    ownerLogin: The GitHub username of the repository owner.
    branches: An array of BranchInfo objects, each containing the following fields:
        name: The name of the branch.
        lastCommitSha: The SHA of the last commit on the branch.

#### 404 Not Found: If the specified GitHub username does not exist.

#### 406 Not Acceptable: If request contains an `Accept` header with a value `application/xml`.