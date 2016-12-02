# validAnnotation
自定义的模型层数据校验注解，提供常见的数据格式校验，如最大长度，最小长度，日期格式，手机号码格式，身份证号格式等。通过注解的方式减轻业务层对数据格式的校验。在模型层使用注解，最后通过ValidateUtil工具封装的静态方法校验对象。