package org.example.leadmanagement.Service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CounsellorQueueService {
    // counsellorId → assigned leads
    private final Map<String, Queue<String>> assignedQueues = new ConcurrentHashMap<>();

    // counsellorId → follow-up leads
    private final Map<String, Queue<String>> followUpQueues =  new ConcurrentHashMap<>();

    public void assignLead(String CounsellorId, String LeadId)
    {
        assignedQueues.computeIfAbsent(CounsellorId, k -> new LinkedList<>()).offer(LeadId);
    }

    public void moveNextLeadToFollowUp(String counsellorId) {
        Queue<String> assignedQueue = assignedQueues.get(counsellorId);
        if (assignedQueue != null && !assignedQueue.isEmpty()) {
            String leadId = assignedQueue.poll(); // remove from assigned queue
            followUpQueues
                    .computeIfAbsent(counsellorId, k -> new LinkedList<>())
                    .offer(leadId); // add to follow-up queue
        }
    }

    public int getBothQueueSize(String counsellorId)
    {
        int assignedSize = assignedQueues.containsKey(counsellorId) ? assignedQueues.get(counsellorId).size() : 0;
        int followSize = (followUpQueues.containsKey(counsellorId) ? followUpQueues.get(counsellorId).size() : 0);
        return assignedSize + followSize;
    }

}

