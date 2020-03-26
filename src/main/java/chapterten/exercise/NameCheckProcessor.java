package chapterten.exercise;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Author: HB
 * @Description: 插入式注解处理器类 - 驼峰式命名检查
 *               若要构造注解处理器, 需要继承抽象类javax.annotation.processing.AbstractProcessor,
 *               并实现process()方法,它是Javac编译器在执行注解处理器代码要调用的过程。
 *               在Java源码中, 插入式注解处理器的初始化过程是在initProcessAnnotations()方法中, 而它的
 *               执行过程是在processAnnotations()方法中完成, 这个方法会判定是否还有新的注解处理器需要
 *               处理, 如果有的话, 就通过JavacProcessing类的doProcessing()方法来生成一个JavaCompiler
 *               对象来进行处理。
 * @CreateDate: 20:58 2020/3/25
 */
// "*"表示支持所有Annotations
@SupportedAnnotationTypes("*")
// 只支持JDK 6的代码
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {
    private NameChecker nameChecker;

    /**
     * @Author: HB
     * @Description: 初始化名称检查器
     * @Date: 21:21 2020/3/25
     * @Params: processingEnv - AbstractProcessor中的一个protected变量, 在注解处理器初始化的时候(init()方法)
     *          创建, 它代表了注解处理器框架提供的一个下上文环境, 要创建新的代码, 向编译器输出信息, 获取其他工具类等
     *          要使用该实例变量。
     * @Returns:
    */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * @Author: HB
     * @Description:  Javac编译器在执行注解处理器代码要调用的过程 - 对输入的语法树的各个节点进行名称检查
     * @Date: 21:04 2020/3/25
     * @Params: annotations - 此注解处理器要处理的注解集合
     * @Params: roundEnv - 可以从该参数中访问到当前这个轮次的抽象语法树节点, 每个语法节点表现为一个Element
     * @Returns:
    */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            for (Element element : roundEnv.getRootElements()){
                nameChecker.checkNames(element);
            }
        }
        // 如果不需要改变/添加抽象语法树的内容,则返回false,通知编译器这个轮次代码未改变,无需构造新的JavaCompiler实例
        return false;
    }
}
