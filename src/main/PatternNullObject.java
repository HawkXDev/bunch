package main;

/**
 * Pattern Null Object
 */
public class PatternNullObject {
    public static void main(String[] args) {
        AbstractEntity realEntity = new RealEntity();
        realEntity.doSomething();  // RealEntity::doSomething

        AbstractEntity unknownEntity = new NullEntity();
        unknownEntity.doSomething();  // no output
    }
}

abstract class AbstractEntity {
    public abstract void doSomething();
}

class RealEntity extends AbstractEntity {
    @Override
    public void doSomething() {
        System.out.println("RealEntity::doSomething");
    }
}

class NullEntity extends AbstractEntity {
    @Override
    public void doSomething() {

    }
}
