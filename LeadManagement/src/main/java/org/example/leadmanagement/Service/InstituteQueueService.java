package org.example.leadmanagement.Service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InstituteQueueService {

    private final Map<String, Queue<String>> instituteQueues = new ConcurrentHashMap<>();

    public void enqueueLead(String instituteId, String leadId) {
        instituteQueues.computeIfAbsent(instituteId, k -> new LinkedList<>()).offer(leadId);
    }

    public String dequeueLead(String instituteId) {
        Queue<String> queue = instituteQueues.get(instituteId);
        if (queue != null && !queue.isEmpty()) {
            return queue.poll();
        }
        return null;
    }
}
