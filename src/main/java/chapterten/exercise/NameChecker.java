package chapterten.exercise;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * @Author: HB
 * @Description:  程序名称规范编译器插件 - 若程序名称不符合规范, 则输出相应的Warning信息
 * @CreateDate: 21:20 2020/3/25
 */

public class NameChecker {
    // 利用messager来输出打印信息
    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    public NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    /**
     * @Author: HB
     * @Description: 对外开放的方法
     * @Date: 9:29 2020/3/26
     * @Params: null
     * @Returns:
    */
    public void checkNames(Element element){
        nameCheckScanner.scan(element);
    }

    /**
     * @Author: HB
     * @Description: 名称检查器实现类, 继承了JDK 6 中提供的ElementScanner6类
     *               将会以Visitor模式访问抽象语法树中的元素, 并分别执行visitType(), visitVariable(),
     *               visitExecutable()方法, 在这三个方法中对各自的命名规则进行检查。
     * @Date: 9:30 2020/3/26
     * @Params:
     * @Returns:
    */
    private class NameCheckScanner extends ElementScanner6<Void,Void>{

        /**
         * @Author: HB
         * @Description: 检查Java类
         * @Date: 9:33 2020/3/26
         * @Params:
         * @Returns:
        */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(), aVoid);
            checkCamelCase(e, true);
            super.visitType(e, aVoid);
            return null;
        }

        /**
         * @Author: HB
         * @Description: 检查变量命名是否合法
         * @Date: 9:45 2020/3/26
         * @Params:
         * @Returns:
        */
        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            // 如果这个Variable是枚举或常量, 则按大写命名检查, 或者按照驼式命名检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)){
                checkAllCaps(e);
            }else{
                checkCamelCase(e, false);
            }
            return null;
        }

        /**
         * @Author: HB
         * @Description: 检查方法命名是否合法
         * @Date: 9:36 2020/3/26
         * @Params:
         * @Returns:
        */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD){
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())){
                    messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法 ‘" + name
                            + "’ 不应当与类目重复,避免与构造函数混淆", e);
                }
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        /**
         * @Author: HB
         * @Description: 判断一个变量是否是常量
         * @Date: 9:51 2020/3/26
         * @Params:
         * @Returns:
        */
        private boolean heuristicallyConstant(VariableElement e){
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE)
                return true;
            else if(e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC,
                    Modifier.STATIC, Modifier.FINAL)))
                return true;
            else{
                return false;
            }
        }

        /**
         * @Author: HB
         * @Description: 检查传入的Element是否符合驼式命名法
         * @Date: 10:05 2020/3/26
         * @Params:
         * @Returns:
        */
        private void checkCamelCase(Element e , boolean initialCaps){
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)){
                previousUpper = true;
                if (!initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称 ‘" + name
                            + "’ 应以小写字母开头", e);
                }
            }else if (Character.isLowerCase(firstCodePoint)){
                if (initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称 ‘" + name
                            + "’ 应以大写字母开头", e);
                }
            } else{
                conventional = false;
            }

            if (conventional){
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)){
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)){
                        if (previousUpper){
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    }else{
                        previousUpper = false;
                    }
                }
            }
            if (!conventional)
                messager.printMessage(Diagnostic.Kind.WARNING, "名称 ‘" + name
                        + "’ 应符合驼式命名", e);
        }

        /**
         * @Author: HB
         * @Description: 大写命名检查, 要求第一个字母必须是大写, 其余可以是下划线或者大写字母
         * @Date: 10:14 2020/3/26
         * @Params:
         * @Returns:
        */
        private void checkAllCaps(Element element){
            String name = element.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if (!Character.isUpperCase(firstCodePoint))
                conventional = false;
            else{
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)){
                    cp = name.codePointAt(i);
                    if (cp == (int)'_'){
                        if (previousUnderscore){
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                            conventional = false;
                            break;
                        }
                    }
                }
            }
            if (!conventional)
                messager.printMessage(Diagnostic.Kind.WARNING, "常量 ‘" + name
                        + "’ 应全部以大写字母或者下划线命名, 并且以字母开头", element);
        }
    }
}
