package org.example.leadmanagement.Service;

import org.springframework.stereotype.Service;

import java.util.*;
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

    public boolean IsQueueEmpty(String instituteId) {
        Queue<String> queue = instituteQueues.get(instituteId);
        return queue == null || queue.isEmpty() || queue.poll() != null;
    }

    public String randomPickInstituteId()
    {
        Random rand = new Random();
        List<String> instituteIds = new ArrayList<>(instituteQueues.keySet());
        return instituteIds.get(rand.nextInt(instituteIds.size()));
    }
}
