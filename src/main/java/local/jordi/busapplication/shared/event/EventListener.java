package local.jordi.busapplication.shared.event;

public interface EventListener <T> {
    void Fire(T t);
}
