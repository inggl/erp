# Enterprise Resource Planning

## About

Sample project

## Features
- Dockerized apps
- Reactive stack
- Monitoring integration

## Prerequisites
- __Docker__
- __Node.js__
- __Yarn 2+__
- __PostgreSQL__
- __Mongo__
  - Replicated instances
- __Go__
- __Keycloak__
- __NVM (Optional)__

## Run
```shell
gradlew gateway:bootRun --args='--debug' && gradlew order:bootRun --args='--debug' && gradlew notification:quarkusDev
```

## Docker
Build
```shell
docker-compose build --no-cache --progress=plain
```

Start
```shell
docker-compose up -d
```

Rebuild and Run
```shell
docker-compose up --build -d
```

Push
```shell
docker-compose push
```

## Modules

#### APIs
- __Gateway__ - Kotlin Spring Boot Project
- __Order__ - Kotlin Spring Boot Project
- __Notification__ - Java Quarkus Project
- __Storage__ - Go Project
- __Report__ - Java Spring Project
- __Tracking__ - Node.js + Express Project
- __Analysis__ - Java Spring Cloud Project

#### Apps
- __Erp App__ - React Project

## Architecture

### Mono repo

#### Git submodules

> Erp App

Add new submodule

```
git submodule add <repo_url> <path>
```

or
```
git submodule add -b <branch> <repo_url> <path>
```

Update submodule

```shell
git submodule update --remote
```

```shell
git submodule foreach --recursive "git checkout develop || true"
```

Pull
```shell
git pull --recurse-submodules
```

#### Gradle

Multi module Gradle project

#### Yarn
Yarn 2+ workspaces + pnp + Lerna

#### Husky

##### Installation

In mono repo root directory run

```shell
yarn add -D husky
```

##### Initialization

```shell
npx husky-init && yarn
```

##### Configuration

Add *package.json* prepare script

```json
{
  "scripts": {
    "postinstall": "husky install"
  }
}
```

###### Pre commit hook

Edit *pre-commit* file changing default script

###### Lint staged

Install

```shell
yarn add -D lint-staged
```

Create *.lintstagedrc.json*

```text
{
    "*.(js|ts)": "eslint --fix"
}
```

## Configuration
#### Yarn timeout
```shell
yarn config set network-timeout 600000 -g
```

### Debugging the Application in a Docker Container
```shell
docker run --name erp-order -e "JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t inggl/erp-order
```

### Git
Disable root project submodule git tracking
```
git update-index --assume-unchanged <path>
```

```
git update-index --no-assume-unchaged <path>
```

Example:
```shell
git update-index --assume-unchanged charts && git update-index --assume-unchanged packages/erp-app && git update-index --assume-unchanged packages/ngx-erp
```

### Security
#### OAuth2

Enable profile *oauth2*

- Keycloak Admin Page and create new realm *erp*.
- Clients > Create new clients *erp-api* and *erp-app* respectively with **confidential** and **public** Access Type.
- Clients > *erp-api* > Add **Valid Redirect URIs** *<http://localhost:8081/login/oauth2/code/erp-api>*
- Clients > *erp-app* > Add **Valid Redirect URIs** *<http://localhost:3000/*>* and **Web Origins** *+*
- Users > Create new *admin* user.
- Roles > Create and assign new *ADMIN* role.