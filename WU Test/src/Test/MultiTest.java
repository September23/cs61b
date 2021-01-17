package Test;

class A {
    public String show(D obj){
        return ("A and D");
    }
    public String show(A obj){
        return ("A and A");
    }
}
class B extends A{
    public String show(A obj){
        return ("B and A");
    }
    public String show(B obj){
        return ("B and B");
    }
}
class C extends B{}
class D extends B{}


public class MultiTest {
    public static void main(String[] args){
        A ab = new B();
        B b = new B();
        C c = new C();
        System.out.println(ab.show(b));
        System.out.println(ab.show(c));
    }

}
