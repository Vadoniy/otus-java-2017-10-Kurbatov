import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Vadoniy on 23.01.2018.
 */
public class JSONSerializer {

    public String toJsonString(TestObject object){
        StringBuilder sb = new StringBuilder();

        if (Objects.isNull(object)){
            return sb.append("").toString();
        }

        sb.append("{");
        List<Field> fieldsArray = Arrays.asList(object.getClass().getDeclaredFields());
        fieldsArray.forEach(field -> {
            try {
                final String fieldstr = String.format("\"%s\":%s", field.getName(), getValue(field, object));
                sb.append(fieldstr);
                sb.append(",");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(",")+1);
        sb.append('}');
        return sb.toString();
    }

    private String getValue(Field field, Object obj) throws IllegalAccessException {
        if (Objects.isNull(obj)){
            return "";
        }
        Object object = field.get(obj);
        Class aClass = object.getClass();
        if (String.class.isInstance(object) || char.class.isInstance(object)){
            return String.format("\"%s\"", object);
        } else if (Boolean.class.isInstance(object) || aClass.isPrimitive() || Number.class.isInstance(object)){
            return object.toString();
        } else if (aClass.isArray() || Collection.class.isInstance(object)){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            List l;

            if (Collection.class.isInstance(object)){
                l = (List) object;
                Iterator iterator = l.iterator();

                while (iterator.hasNext()){
                    stringBuilder.append(iterator.next());
                    stringBuilder.append(",");
                }

            } else {
                l = new ArrayList();

                for (int i = 0; i<Array.getLength(object); i++){
                    l.add(Array.get(object, i));
                }

                for (int i = 0; i<l.toArray().length; i++){
                    stringBuilder.append(l.get(i));
                    stringBuilder.append(",");
                }
            }

            stringBuilder.delete(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf(",")+1);
            stringBuilder.append("]");
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
