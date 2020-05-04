# MyBatis_Senior
## MyBatis工作流程
1. `new SqlSessionFactoryBuilder().build(inputstream);`
    * 先更据全局配置文件和映射配置文件初始化一个Configuration对象（包含配置文件里的所有标签信息）
    * MapperRegister包含所有映射文件信息
    * Configuration里面包含的每一个MappedStatement代表一个增删改查标签
    * 将Configuration对象传递给DefaultSqlSessionFactory构造器
    * 返回DefaultSqlSessionFactory实例
2. `sqlSessionFactory.openSession()`
    * 实际上是DefaultSqlSessionFactory对象实例调用openSessionFromDataSource
    *       Environment environment = this.configuration.getEnvironment();
            TransactionFactory transactionFactory = this.getTransactionFactoryFromEnvironment(environment);
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            Executor executor = this.configuration.newExecutor(tx, execType);
            var8 = new DefaultSqlSession(this.configuration, executor, autoCommit);
    * 获取环境、获取事务处理、生成执行器、返回DefaultSqlSession实例
    * 生成执行器
    *       executorType = executorType == null ? this.defaultExecutorType : executorType;
            executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
            Object executor;
            if (ExecutorType.BATCH == executorType) {
                executor = new BatchExecutor(this, transaction);
            } else if (ExecutorType.REUSE == executorType) {
                executor = new ReuseExecutor(this, transaction);
            } else {
                executor = new SimpleExecutor(this, transaction);
            }
   
            if (this.cacheEnabled) {
                executor = new CachingExecutor((Executor)executor);
            }
    
            Executor executor = (Executor)this.interceptorChain.pluginAll(executor);
            return executor;
    * 返回DefaultSqlSession实例  
    `new DefaultSqlSession(this.configuration, executor, autoCommit);`
3. `sqlSession.getMapper(UserMapper.class)`
    * 实际调用DefaultSqlSession.getMapper()获取对应mapper的代理对象（MapperProxy）
        1. 从configuration里的MapperRegister对象调用getMapper()
        2. 返回mapperProxyFactory.newInstance(sqlSession)
4. `userMapper.selectByPrimaryKey(1)`
    * 执行增删改查操作，实际上调用MapperProxy的invoke方法
        * 如果方法为Object方法，则直接调用
        * 如果是Default方法也直接调用
        * 最后是抽象方法（也就是有对应xml中定义的sql语句的方法）
            1. 这里将接口方法转化为MapperMethod
            2. 执行增删改查过程中executor会创建StatementHandler、ParameterHandler、ResultHandler
                * StatementHandler执行sql语句
                * ParameterHandler通过typeHandler设置sql语句中的参数
                * ResultHandler通过typeHandler封装sql查询出的结果
> MyBatis执行流程中最重要的四个关键对象Executor、StatementHandler、ParameterHandler、ResultHandler
>    * 这四个对象在创建完成之前都会被一个方法过滤一遍
>    * this.interceptorChain.pluginAll(XXXHandler/executor);
>    * 插件的重要点（实际上是一个AOP的一个切面）

## MyBatis分页插件
### 插件的基本结构
* 实现Interceptor接口
* @Intercepts({@Signature(type,method,args)})告诉拦截器拦截哪个类哪个方法
* 实现：
    * intercept() --- 偷梁换柱
    * plugin() --- 包装
    * setProperties() --- 获取全局配置文件里传入的参数
* 在全局配置文件中配置`<plugin>`
### PageHelper使用
* PageHelperTest
* Page<User> pager = PageHelper.startPage(,); --- 有关的分页信息
* PageInfo<User> info = new PageInfo<>(pager); --- 信息更加详细
## 调用存储过程
* ProcedureTest
* statement="CALLABLE"
* call
    * mode
        * IN
        * OUT
    * JdbcType
## typeHandler处理枚举类型