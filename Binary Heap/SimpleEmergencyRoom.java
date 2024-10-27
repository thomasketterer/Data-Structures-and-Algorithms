package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        if (patients.isEmpty()) {
            return null;
        }
        int maxPriority = 0;
        Patient current = new Patient(null, null);
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPriority().compareTo(maxPriority) == 1) {
                current = patients.get(i);
                maxPriority = i;
            }
        }
        patients.remove(maxPriority);
        return current;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
