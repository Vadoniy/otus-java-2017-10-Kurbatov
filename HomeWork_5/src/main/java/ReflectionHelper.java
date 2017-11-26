import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class ReflectionHelper {

    static boolean checkAnnotationTest(Method method, Class annotationClass){
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation ann : annotations) {
            if (annotationClass.equals(ann.annotationType())){
                return true;
            }
        }
        return false;
    }
}
