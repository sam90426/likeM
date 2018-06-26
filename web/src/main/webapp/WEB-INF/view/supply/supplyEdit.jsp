<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>app用户新增/编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css" />
    <link href="/assets/js/KindEditor/themes/default/default.css" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
    <script src="/assets/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="/assets/js/KindEditor/kindeditor-all.js"></script>
    <script src="/assets/js/KindEditor/lang/zh-CN.js"></script>
    <script src="/assets/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
</head>
<body>
<form id="form1" method="post">
    <div style="padding-left: 11px; padding-bottom: 5px; width: 750px;">
        <input name="id" class="mini-hidden" />
        <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
            <thead>
            <tr>
                <th colspan="4">
                    供应商信息
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th style="width: 14%">
                    供应商名称：
                </th>
                <td style="width: 36%">
                    <input id="supplyName" name="supplyName" class="mini-textbox" required="true" emptytext="请输入供应商名称" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    供应商类型：
                </th>
                <td>
                    <input id="supplyType" name="supplyType" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'极速贷'},{id:'2',text:'工薪贷'},{id:'3',text:'白领贷'}]" emptytext="请选择供应商类型" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    链接地址：
                </th>
                <td style="width: 36%">
                    <input id="linkUrl" name="linkUrl" class="mini-textbox" required="true" emptytext="请输入链接地址" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    状态：
                </th>
                <td style="width: 36%">
                    <input id="state" name="state" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'正常'},{id:'2',text:'停用'},{id:'3',text:'资讯'},{id:'4',text:'商品'}]" emptytext="请选择状态" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    借款金额：
                </th>
                <td style="width: 36%">
                    <input id="minMoney" name="minMoney" class="mini-spinner" minValue="0.01" maxValue="1000000" required="true" vtype="int" emptytext="请输入最小金额" style="width: 95px;"/>&nbsp;-
                    <input id="maxMoney" name="maxMoney" class="mini-spinner" minValue="0.01" maxValue="1000000" required="true" vtype="int" emptytext="请输入最大金额" style="width: 94px;"/>元
                </td>
                <th style="width: 14%">
                    借款期限：
                </th>
                <td style="width: 36%">
                    <input id="minLoanTerm" name="minLoanTerm" class="mini-spinner" minValue="1" maxValue="100000" required="true" vtype="int" emptytext="请输入借款期限起始天数" style="width: 95px;"/>&nbsp;-
                    <input id="maxLoanTerm" name="maxLoanTerm" class="mini-spinner" minValue="1" maxValue="100000" required="true" vtype="int" emptytext="请输入借款期限最大天数" style="width: 94px;"/>天
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    利息/利率：
                </th>
                <td style="width: 36%">
                    <input id="rate" name="rate" class="mini-textbox" required="true" vtype="float" emptytext="请输入利息/利率" style="width: 200px;"/>%
                </td>
                <th style="width: 14%">
                    利率单位：
                </th>
                <td>
                    <input id="rateUnit" name="rateUnit" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'日'},{id:'2',text:'月'},{id:'3',text:'年'}]" emptytext="请选择利率单位" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    排序编号：
                </th>
                <td>
                    <input id="orderIndex" name="orderIndex" class="mini-textbox" required="true" vtype="int" emptytext="请输入排序编号" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    点击量：
                </th>
                <td>
                    <input id="hits" name="hits" class="mini-textbox" required="true" vtype="int" emptytext="请输入点击量" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    是否推荐：
                </th>
                <td style="width: 36%">
                    <input id="recommend" name="recommend" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'是'},{id:'2',text:'否'}]" emptytext="请选择" />
                </td>
                <th style="width: 14%">
                    评分：
                </th>
                <td>
                    <input id="score" name="score" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'1颗星'},{id:'2',text:'2颗星'},{id:'3',text:'3颗星'},{id:'4',text:'4颗星'},{id:'5',text:'5颗星'}]" emptytext="请选择利率单位" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    放款速度：
                </th>
                <td style="width: 36%">
                    <input id="lendingSpeed" name="lendingSpeed" class="mini-spinner" required="true" minValue="0" maxValue="100000" vtype="int" emptytext="请输入放款速度" style="width: 200px;"/>分钟
                </td>
                <th style="width: 14%">
                    评价人数：
                </th>
                <td style="width: 36%">
                    <input id="evaluateNum" name="evaluateNum" class="mini-spinner" required="true" vtype="int"  minValue="0" maxValue="10000000" emptytext="请输入评价人数" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    通过概率：
                </th>
                <td style="width: 36%">
                    <input id="passProbability" name="passProbability" class="mini-textbox" required="true" vtype="range:0,100" emptytext="请输入通过概率" style="width: 200px;"/>%
                </td>
                <th style="width: 14%">
                    攻略排序：
                </th>
                <td style="width: 36%">
                    <input id="raidersIndex" name="raidersIndex" class="mini-spinner"  minValue="0" maxValue="10000000"  required="true" vtype="int" emptytext="请输入攻略排序" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    欢迎程度：
                </th>
                <td style="width: 36%">
                    <input id="welcomeDegree" name="welcomeDegree" class="mini-textbox" required="true" vtype="range:0,100" emptytext="请输入欢迎程度" style="width: 200px;"/>%
                </td>
                <th style="width: 14%">
                    热门关键字：
                </th>
                <td style="width: 36%">
                    <input id="hotKeyWord" name="hotKeyWord" class="mini-textbox" required="true" emptytext="请输入热门关键字" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    放款速度：
                </th>
                <td style="width: 36%">
                    <input id="lendingSpeedBar" name="lendingSpeedBar" class="mini-textbox" required="true" vtype="range:0,100" emptytext="请输入通过概率" style="width: 200px;"/>%
                </td>
                <th style="width: 14%">
                    是否查征信：
                </th>
                <td style="width: 36%">
                    <input id="isCredit" name="isCredit" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'是'},{id:'2',text:'否'}]" emptytext="请选择" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    基本信息认证：
                </th>
                <td style="width: 36%">
                    <input id="isEssential" name="isEssential" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
                <th style="width: 14%">
                    人脸识别认证：
                </th>
                <td style="width: 36%">
                    <input id="isFace" name="isFace" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    身份证认证：
                </th>
                <td style="width: 36%">
                    <input id="isIdentity" name="isIdentity" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
                <th style="width: 14%">
                    运营商认证：
                </th>
                <td style="width: 36%">
                    <input id="isOperator" name="isOperator" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    芝麻信用认证：
                </th>
                <td style="width: 36%">
                    <input id="isSesame" name="isSesame" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
                <th style="width: 14%">
                    银行卡认证：
                </th>
                <td style="width: 36%">
                    <input id="isBank" name="isBank" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'需要'},{id:'2',text:'不需要'}]" emptytext="请选择" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    LOGO：
                </th>
                <td colspan="3">
                    <img id="imgPicUrl" name="imgPicUrl" alt="广告位图片" src="/assets/img/NullPhoto.jpg" style="height: 100px"/><span style="color: Gray"></span>
                    <input id="logo" name="logo" class="mini-hidden" value=""/><br/>
                    <a class="mini-button" id="btnSingleUpFile" tooltip="图片上传">图片上传</a>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    简介：
                </th>
                <td colspan="3">
                    <textarea id="intro" name="intro" class="mini-textarea" style="width: 650px; height:100px;"></textarea>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    借款审核细节：
                </th>
                <td colspan="3">
                    <textarea id="examineDetails" name="examineDetails" class="mini-textarea" style="width: 650px; height:100px;"></textarea>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    申请条件&要点：
                </th>
                <td colspan="3">
                    <textarea id="applyCondition" name="applyCondition" class="mini-textarea" style="width: 650px; height:100px;"></textarea>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    新手指导：
                </th>
                <td colspan="3">
                    <textarea id="noviceGuidance" name="noviceGuidance" class="mini-textarea" style="width: 650px; height:100px;"></textarea>
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    产品优势：
                </th>
                <td colspan="3">
                    <textarea id="productAdvantage" name="productAdvantage" class="mini-textarea" style="width: 650px; height:100px;"></textarea>
                </td>
            </tr>
            <tr>
                <th>
                    攻略：
                </th>
                <td style="width: 86%" colspan="3">
                    <textarea id="raiders" name="raiders" style="width: 85%; height: 300px; visibility: hidden;"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="text-align: center; padding: 10px; width: 750px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a>
    </div>
</form>
<script src="/assets/pagejs/info/supply/supplyEdit.js?v=1.2" type="text/javascript"></script>
</body>
</html>

