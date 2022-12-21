import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class Annotation {
    public static void main(String[] args) {
        Address address = new Address("Pushkin", 1, 1);
        Annotation annotation = new Annotation();
        annotation.MultipleCall(address);
    }

    public void MultipleCall(Object object) {
        try {
            checkMultipleCallAccess(object);
            InitMultipleCall(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkMultipleCallAccess(Object object) throws Exception {
        if (Objects.isNull(object)){
            throw new Exception("Object is null");
        }

        Class<?> c = object.getClass();
        if (!c.isAnnotationPresent(MultipleCallAccess.class)){
            throw new Exception(c.getName() + " can't use multiple call");
        }
    }

    private void InitMultipleCall(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> c = object.getClass();
        for (Method m : c.getDeclaredMethods()){
            if (m.isAnnotationPresent(MultipleCall.class)){
                m.setAccessible(true);
                int modifiers = m.getModifiers();

                if (Objects.equals(Modifier.toString(modifiers), "protected")
                 || Objects.equals(Modifier.toString(modifiers), "private")){
                    int value = m.getAnnotation(MultipleCall.class).value();
                    for (int i = 0; i < value; ++i) {
                     m.invoke(object);
                    }
                }
            }
        }
    }
}
