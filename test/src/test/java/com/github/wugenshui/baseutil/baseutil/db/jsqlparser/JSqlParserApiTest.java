package com.github.wugenshui.baseutil.baseutil.db.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.GroupByElement;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JSqlParser基础API测试
 * @author : chenbo
 * @date : 2023-11-12
 */
class JSqlParserApiTest {
    @Test
    void simple() {
        // 单表全量
        Table table = new Table("test");
        Select select = SelectUtils.buildSelectFromTable(table);
        System.err.println(select); // SELECT * FROM test

        // 指定列查询
        Select buildSelectFromTableAndExpressions = SelectUtils.buildSelectFromTableAndExpressions(new Table("test"), new Column("col1"), new Column("col2"));
        System.err.println(buildSelectFromTableAndExpressions); // SELECT col1, col2 FROM test

        // WHERE =
        EqualsTo equalsTo = new EqualsTo(); // 等于表达式
        equalsTo.setLeftExpression(new Column(table, "user_id")); // 设置表达式左边值
        equalsTo.setRightExpression(new StringValue("123456"));// 设置表达式右边值
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody(); // 转换为更细化的Select对象
        plainSelect.setWhere(equalsTo);
        System.err.println(plainSelect);//  SELECT * FROM test WHERE test.user_id = '123456'

        // WHERE  != <>
        NotEqualsTo notEqualsTo = new NotEqualsTo();
        notEqualsTo.setLeftExpression(new Column(table, "user_id")); // 设置表达式左边值
        notEqualsTo.setRightExpression(new StringValue("123456"));// 设置表达式右边值
        PlainSelect plainSelectNot = (PlainSelect) select.getSelectBody();
        plainSelectNot.setWhere(notEqualsTo);
        System.err.println(plainSelectNot);//  SELECT * FROM test WHERE test.user_id <> '123456'

        // 其他运算符, 参考上面代码添加表达式即可
        GreaterThan gt = new GreaterThan(); // ">"
        GreaterThanEquals ge = new GreaterThanEquals(); // ">="
        MinorThan mt = new MinorThan(); // "<"
        MinorThanEquals me = new MinorThanEquals();// "<="
        IsNullExpression isNull = new IsNullExpression(); // "is null"
        isNull.setNot(true);// "is not null"
        LikeExpression nlike = new LikeExpression();
        nlike.setNot(true); // "not like"
        Between bt = new Between();
        bt.setNot(true);// "not between"

        // WHERE LIKE
        LikeExpression likeExpression = new LikeExpression(); // 创建Like表达式对象
        likeExpression.setLeftExpression(new Column("username")); // 表达式左边
        likeExpression.setRightExpression(new StringValue("张%")); // 右边表达式
        PlainSelect plainSelectLike = (PlainSelect) select.getSelectBody();
        plainSelectLike.setWhere(likeExpression);
        System.err.println(plainSelectLike); // SELECT * FROM test WHERE username LIKE '张%'

        // WHERE IN
        Set<String> deptIds = new HashSet<>(); // 创建IN范围的元素集合
        deptIds.add("0001");
        deptIds.add("0002");
        ItemsList itemsList = new ExpressionList(deptIds.stream().map(StringValue::new).collect(Collectors.toList())); // 把集合转变为JSQLParser需要的元素列表
        InExpression inExpression = new InExpression(new Column("dept_id "), itemsList); // 创建IN表达式对象，传入列名及IN范围列表
        PlainSelect plainSelectIn = (PlainSelect) select.getSelectBody();
        plainSelectIn.setWhere(inExpression);
        System.err.println(plainSelectIn); // SELECT * FROM test WHERE dept_id IN ('0001', '0002')

        // WHERE BETWEEN AND
        Between between = new Between();
        between.setBetweenExpressionStart(new LongValue(18)); // 设置起点值
        between.setBetweenExpressionEnd(new LongValue(30)); // 设置终点值
        between.setLeftExpression(new Column("age")); // 设置左边的表达式，一般为列
        PlainSelect plainSelectBetween = (PlainSelect) select.getSelectBody();
        plainSelectBetween.setWhere(between);
        System.err.println(plainSelectBetween); // SELECT * FROM test WHERE age BETWEEN 18 AND 30

        //  WHERE AND 多个条件结合,都需要成立
        AndExpression andExpression = new AndExpression(); // AND 表达式
        andExpression.setLeftExpression(equalsTo); // AND 左边表达式
        andExpression.setRightExpression(between);  // AND 右边表达式
        PlainSelect plainSelectAnd = (PlainSelect) select.getSelectBody();
        plainSelectAnd.setWhere(andExpression);
        System.err.println(plainSelectAnd); //  SELECT * FROM test WHERE test.user_id = '123456' AND age BETWEEN 18 AND 30

        //  WHERE OR 多个条件满足一个条件成立返回
        OrExpression orExpression = new OrExpression();// OR 表达式
        orExpression.setLeftExpression(equalsTo); // OR 左边表达式
        orExpression.setRightExpression(between);  // OR 右边表达式
        PlainSelect plainSelectOr = (PlainSelect) select.getSelectBody();
        plainSelectOr.setWhere(orExpression);
        System.err.println(plainSelectOr); // SELECT * FROM test WHERE test.user_id = '123456' OR age BETWEEN 18 AND 30

        // ORDER BY 排序
        OrderByElement orderByElement = new OrderByElement(); // 创建排序对象
        orderByElement.isAsc(); //  设置升序排列 从小到大
        orderByElement.setExpression(new Column("col01")); // 设置排序字段
        PlainSelect plainSelectOrderBy = (PlainSelect) select.getSelectBody();
        plainSelectOrderBy.addOrderByElements(orderByElement);
        System.err.println(plainSelectOrderBy); // SELECT * FROM test WHERE test.user_id = '123456' OR age BETWEEN 18 AND 30 ORDER BY col01
    }

    @Test
    void testSelectManyTable() {
        Table t1 = new Table("tab1").withAlias(new Alias("t1").withUseAs(true)); // 表1
        Table t2 = new Table("tab2").withAlias(new Alias("t2", false)); // 表2
        PlainSelect plainSelect = new PlainSelect().addSelectItems(new AllColumns()).withFromItem(t1); // SELECT * FROM tab1 AS t1

        // JOIN ON 如果表中有至少一个匹配，则返回行
        Join join = new Join(); // 创建Join对象
        join.withRightItem(t2); // 添加Join的表 JOIN t2 =>JOIN tab2 t2
        EqualsTo equalsTo = new EqualsTo(); // 添加 = 条件表达式  t1.user_id  = t2.user_id
        equalsTo.setLeftExpression(new Column(t1, "user_id "));
        equalsTo.setRightExpression(new Column(t2, "user_id "));
        join.addOnExpression(equalsTo);// 添加ON
        plainSelect.addJoins(join);
        Assertions.assertEquals("SELECT * FROM tab1 AS t1 JOIN tab2 t2 ON t1.user_id  = t2.user_id ", plainSelect.toString());

        // 设置join参数可实现其他类型join
        // join.setLeft(true); LEFT JOIN
        // join.setRight(true);  RIGHT JOIN
        // join.setFull(true); FULL JOIN
        // join.setInner(true);
    }

    String sql1 = "select t1.f1,t1.f2,t2.id,count(*) from table t1 left join table1 t2 right join (select * from table2) t3 where t1.id='12121' or (t1.id between 1 and 3 and t1.id>'22112') group by t.f1 order by t.f1 desc,tf2 asc limit 1,20";

    @Test
    void testSimpleSelectSql() {
        System.out.println("=================测试查询==================");
        try {
            Select select = (Select) CCJSqlParserUtil.parse(sql1);
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(select);
            // 获取到查询sql中的所有表名,下面的逻辑是对sql的细致拆分
            System.out.println("表名:" + tableList);
            if (select.getSelectBody() instanceof PlainSelect) { // 普通查询
                // 复杂sql会多次调用此处方法，所以抽出作为公共类使用
                getSelectMsg(select);
            } else if (select.getSelectBody() instanceof WithItem) { // WITH语句

            } else if (select.getSelectBody() instanceof SetOperationList) { // INTERSECT、EXCEPT、MINUS、UNION语句
                SetOperationList setOperationList = (SetOperationList) select.getSelectBody();
                List<SelectBody> selects = setOperationList.getSelects();
                for (int i = 0; i < selects.size(); i++) {
                    // 此处又是符合普通sql的拆解逻辑
                    getSelectMsg(select);
                }
            } else if (select.getSelectBody() instanceof ValuesStatement) { // VALUES语句

            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        System.out.println("=================测试查询==================");
    }

    public static void getSelectMsg(Select select) {
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<Join> joins = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                if (rightItem instanceof Table) {
                    Table table = (Table) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         表：" + table.getName() + "           别名：" + table.getAlias());
                } else if (rightItem instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         子查询：" + subSelect.getSelectBody() + "           别名：" + rightItem.getAlias());
                }
            }
        }
        List<SelectItem> selectItems = plain.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof AllColumns) {
                System.out.println("获取的是表中的全部列:  * ");
                continue;
            }
            SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
            Expression expression = selectExpressionItem.getExpression();
            //判断表达式是否是函数
            if (expression instanceof Function) {
                Function function = (Function) expression;
                NamedExpressionList namedParameters = function.getNamedParameters();
                if (namedParameters != null) {
                    List<Expression> expressions = namedParameters.getExpressions();
                    System.out.println(expressions);
                }
                System.out.println("函数：" + ((Function) expression).getName());
                boolean allColumns = function.isAllColumns();
                System.out.println("传入的是全部列：" + allColumns);
                //判断表达式是否是列
            } else if (expression instanceof Column) {
                System.out.println("查询值：" + ((Column) expression).getColumnName());
            }
        }
//        System.out.println("表名:" + tableList);
        Expression where = plain.getWhere();
        if (where != null) {
            System.out.println("条件:" + where);
        }

        //排序
        List<OrderByElement> orderByElements = plain.getOrderByElements();
        if (Objects.nonNull(orderByElements)) {
            for (OrderByElement orderByElement : orderByElements) {
                Expression expression = orderByElement.getExpression();
                if (expression instanceof Column) {
                    Column column = (Column) (expression);
                    System.out.println("排序字段:" + column.getColumnName() + "," + (orderByElement.isAsc() ? "正序" : "倒序"));
                }
            }
        }


        //获取分组
        GroupByElement groupBy = plain.getGroupBy();
        if (Objects.nonNull(groupBy)) {
            List<Expression> groupByExpressions = groupBy.getGroupByExpressions();
            for (Expression groupByExpression : groupByExpressions) {
                if (groupByExpression instanceof Column) {
                    Column column = (Column) (groupByExpression);
                    System.out.println("分组字段:" + column.getColumnName());
                }
            }
        }

        //分页
        Limit limit = plain.getLimit();
        if (Objects.nonNull(limit)) {
            System.out.println("行:" + limit.getRowCount());
            System.out.println("偏移量:" + limit.getOffset());
        }
    }

    public static String joinTypeStr(Join join) {
        if (join.isLeft()) {
            return "左连接";
        }
        if (join.isRight()) {
            return "左连接";
        }
        if (join.isFull()) {
            return "全连接";
        }
        if (join.isCross()) {
            return "交叉连接";
        }
        return null;
    }
}
