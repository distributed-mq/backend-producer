package com.chaeiou.backend_producer.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j // ✅ Lombok의 로깅 어노테이션
@RestController
@RequestMapping("/api/v1/tasks") // ✅ 엔드포인트 변경
public class TaskController {

    private final List<String> tasks = new ArrayList<>();

    @PostMapping("/add")
    public Map<String, Object> addTasks(@RequestBody Map<String, Integer> request) {
        int count = request.getOrDefault("count", 10);
        int startIdx = tasks.size() + 1;

        for (int i = 0; i < count; i++) {
            tasks.add("Task " + (startIdx + i));
            log.info("🟡 작업 추가됨: Task {}", startIdx + i);

            // ✅ MQ에 작업을 천천히 넣기 (1초 딜레이)
            try {
                Thread.sleep(1000); // 1초씩 대기하면서 처리
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", count + " tasks added.");
        response.put("tasks", tasks);
        return response;
    }

    @GetMapping("/list") // ✅ 작업 조회 API 추가
    public Map<String, Object> getTasks() {
        log.info("📌 현재 작업 리스트 조회 요청");

        Map<String, Object> response = new HashMap<>();
        response.put("totalTasks", tasks.size());
        response.put("tasks", tasks);
        return response;
    }
}