<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>蜂贷超市-签名生成</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0, maximum-scale=2.0"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form class="form-horizontal">
            <div class="form-group" v-for="n in paramCount">
                <label class="col-sm-1 control-label" v-bind:for="'key'+n" v-text="'key'+n"></label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" v-bind:id="'key'+n" v-bind:name="'key'+n" v-model="paramDic[n-1].key"/>
                </div>
                <label class="col-sm-1 control-label" v-bind:for="'value'+n" v-text="'value'+n"></label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" v-bind:id="'value'+n" v-bind:name="'value'+n" v-model="paramDic[n-1].value"/>
                </div>
                <div class="col-sm-6" v-if="n===paramCount">
                    <button type="button" class="btn btn-primary" v-on:click="addParam">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true">添加参数</span>
                    </button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">sign</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" v-model="sign"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-11">
                    <button type="button" class="btn btn-primary" v-on:click="run">生成签名</button>
                </div>
            </div>
        </form>
    </div>
    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        var vm = new Vue({
            el:'.container',
            data:{
                paramCount:1,
                paramDic:[{key:'',value:''}],
                sign:''
            },
            methods:{
                addParam:function(){
                    this.paramCount++;
                    this.paramDic.push({key:'',value:''})
                },
                run:function(){
                    var data = {};
                    this.paramDic.forEach(function(item){
                        if(item.key){
                            data[item.key]=item.value;
                        }
                    });
                    $.ajax({
                        url:'/info/getSign',
                        type:'POST',
                        dataType:'JSON',
                        data:data,
                        success:function(response){
                            if(response.code==0){
                                var sign = response.data["sign"];
                                console.log(sign)
                                vm.$data.sign=sign;
                            }
                        }
                    })
                }
            }
        })
    </script>
</body>
</html>
