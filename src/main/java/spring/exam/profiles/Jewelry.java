package spring.exam.profiles;



public class Jewelry implements Valuable {

    @Override
    public void Hi() {
        log.info("Hi ! I'm Jewelry");
    }
}
