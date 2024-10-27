package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Part 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (Prioritized<V, P> entry : initialEntries) {
            enqueue(entry.getValue(), entry.getPriority());
        }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient(value, priority);
        _heap.add(patient);
        if (_heap.size() == 1) {
            return;
        }
        while (patient.getPriority().compareTo(_heap.get((_heap.indexOf(patient) - 1) / 2).getPriority()) > 0) {
            Collections.swap(_heap, _heap.indexOf(patient), ((_heap.indexOf(patient) -1) /2));
        }

    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient patient = new Patient(value);
        _heap.add(patient);
        if (_heap.size() == 1) {
            return;
        }
        while (patient.getPriority().compareTo(_heap.get((_heap.indexOf(patient) - 1) / 2).getPriority()) > 0) {
            Collections.swap(_heap, _heap.indexOf(patient), ((_heap.indexOf(patient) -1) /2));
        }

    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }
        if (_heap.size() == 1) {
            return _heap.remove(0).getValue();
        }

        Prioritized<V, P> end = _heap.get(_heap.size() -1);
        V max = _heap.get(0).getValue();
        _heap.set(0, end);
        _heap.remove(_heap.size() - 1);
        int hPrio = 0;
        while (true) {
            int leftI = 2 * hPrio + 1;
            if (leftI >= _heap.size()) {
                return max;
            }
            int rIndex = leftI + 1;
            int bIndex = leftI;
            if (rIndex < _heap.size() && _heap.get(leftI).getPriority().compareTo(_heap.get(rIndex).getPriority()) < 0) {
                bIndex = rIndex;
            }
            if (_heap.get(hPrio).getPriority().compareTo(_heap.get(bIndex).getPriority()) < 0) {
                Prioritized<V, P> tp = _heap.get(hPrio);
                _heap.set(hPrio, _heap.get(bIndex));
                _heap.set(bIndex, tp);
                hPrio = bIndex;
            } else {
                return max;
            }
        }
    }

    // TODO: getMax
    @Override
    public V getMax() {
       if (_heap.isEmpty()) {
           return null;
       }
       return _heap.get(0).getValue();
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
