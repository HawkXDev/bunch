package dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Dynamic Proxy pattern
 */
public class Main {
    public static void main(String[] args) {
        // create original object
        Man vasia = new Man("Vasya", 30, "Saint Petersburg", "Russia");

        // get the class loader from the original object
        ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

        // get all the interfaces than the original object implements
        Class[] interfaces = vasia.getClass().getInterfaces();

        // create a proxy for our object vasia
        Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces,
                new PersonInvocationHandler(vasia));

        // Call one of the methods of the proxy object from our original object
        proxyVasia.introduce(vasia.getName());
    }
}
