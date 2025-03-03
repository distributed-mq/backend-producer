# Producer BE

## 프로젝트 내 역할
Producer BE는 클라이언트(FE)로부터 요청을 받아 RabbitMQ에 작업을 전송하는 역할을 수행한다.

## 제공하는 API

### 1. 작업 추가 API
- Method: POST
- Endpoint: /api/v1/tasks/add
- Request Body:  
  {
  "count": 10
  }
- Response:
  {
  "message": "10 tasks added.",
  "tasks": ["Task 1", "Task 2", ...]
  }
- 설명:
    - 요청 받은 작업 개수만큼 메시지 큐에 전송한다.
    - 기본적으로 10개 추가하지만, count 값을 조절하면 원하는 만큼 추가할 수 있다.

### 2. 메시지 큐 조회 API
- Method: GET
- Endpoint: /api/v1/tasks/queue
- Response:
  {
  "totalTasks": 50,
  "tasks": ["Task 1", "Task 2", ...]
  }
- 설명:
    - 현재 메시지 큐에 몇 개의 작업이 쌓여 있는지 조회하는 API.
    - 최대 100개의 작업을 보관할 수 있다.

### 3. 작업 처리 현황 조회 API
- Method: GET
- Endpoint: /api/v1/tasks/processed
- Response:
  {
  "processedTasks": 30
  }
- 설명:
    - Consumer BE 에서 처리된 작업 개수를 반환한다.
    - 이 값을 통해 백엔드에서 정상적으로 메시지를 처리하고 있는지 확인 가능.