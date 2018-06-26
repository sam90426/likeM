<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/assets/css/base.css">
    <link rel="stylesheet" href="/assets/css/commonF.css">
    <link rel="stylesheet" href="/assets/css/register.css?v=1.0">
    <script src="/assets/js/flexible.js" type="text/javascript"></script>
</head>
<body>
    <div class="wrap">
        <div class="header">
            <div class="back" v-on:click="back">
                <i class="icon icon-back"></i>
            </div>
            <div class="title">蜂优贷注册</div>
            <div class="menu"></div>
        </div>
        <div class="content">
            <div class="banner"></div>
            <div class="form-box">
                <div class="input-box">
                    <div class="input-item">
                        <label for="mobile">手机号</label>
                        <input type="text" v-model="mobile">
                    </div>
                    <div class="input-item">
                        <label for="mobile">验证码</label>
                        <input type="text" v-model="code">
                        <button class="btn-getCode" v-on:click="getCode" v-text="countDown===0?'获取验证码':'再次获取('+countDown+')'" v-bind:disabled="countDown!==0">获取验证码</button>
                    </div>
                </div>
                <button class="btn-register" v-on:click="go">前往申请额度<i class="icon icon-go"></i></button>
                <p class="register-xy" v-on:click="agree">点击提交申请即同意<span class="text-tk"><<蜂优贷服务条款及保险公司信息承诺>></span></p>
            </div>
            <div class="info">
            </div>
        </div>
        <div class="module" v-show="showCodeBox">
            <div class="module-box">
                <span class="close" v-on:click="closeCodeBox"></span>
                <div class="code-box">
                    <div class="row">
                        <p>请输入以下验证码</p>
                    </div>
                    <div class="row">
                        <input type="text" v-model="vcode" />
                    </div>
                    <div class="row">
                        <img v-bind:src="codeImgUrl" alt="code"/>
                        <a href="javascript:void(0)" class="btn-refresh" v-on:click="refreshCode">点击换一张</a>
                    </div>
                    <div class="row">
                        <a href="javascript:void(0)" class="btn-confirm" v-on:click="confirmCode">确定</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="module" v-show="showXyBox">
            <div class="xy-box">
                <h1>蜂优贷服务协议</h1>
                <div class="content">
                    <p>郑重提示，本文系您与“蜂优贷”(以下简称平台)之间的法律协议，请您认真阅读并理解本协议。您通过平台点击确认本协议的，即表示您同意遵循本协议的所有约定，本协议在您和平台之间具有法律约束力。如发生法律纠纷，您不得以未仔细阅读或理解本协议为由进行抗辩。</p>
                    <p class="title">第一条 用户确认及服务接纳</p>
                    <p>1.1 注册用户需满足下列条件：</p>
                    <p>(1)中华人民共和国大陆地区居民(不包含台、港、澳地区)；</p>
                    <p>(2)具备接受平台各项服务，履行相应义务的完全民事权利能力及行为能力。</p>
                    <p>1.2 用户需接受平台全部协议条款及各项平台规则，方可成为平台注册用户，接受平台提供的各项服务。</p>
                    <p>1.3 用户确认本协议全部条款并完成注册的，视为用户符合注册条件，能够独立承担因接受平台服务所产生权利义务。</p>
                    <p>1.4 平台保留在中华人民共和国现行有效之法律、法规范围内接受、拒绝、终止/中止用户接受平台服务之资格。</p>
                    <p class="title">第二条 用户注册信息</p>
                    <p>2.1 用户首次通过平台提交个人信息并确认本协议的，即成为平台注册用户。</p>
                    <p>2.2 用户应自行如实向平台提供注册信息。用户应当确保其提供的注册信息真实、准确、完整、合法有效。如用户提供的注册信息不合法、不真实、不准确、不详尽，用户需承担由此产生的相应责任及后果，平台保留终止注册用户资格的权利。</p>
                    <p>2.3 用户认可平台收集及储存用户的资料及信息，包含但不限于用户本人提交的资料及信息以及平台自行收集的用户资料及信息。平台收集、储存用户资料及信息的目的在于更加效率及便利地为用户提供平台服务，平台不得将用户资料及信息用于其他目的。</p>
                    <p>2.4 平台应当采取不低于一般行业惯例对于用户的资料及信息进行保护，但因不可抗力所导致的用户资料及信息泄露(包含但不限于黑客攻击、第三方导致的系统缺陷等)，平台不承担相应的责任。</p>
                    <p>2.5 平台有义务根据行政、司法机关的要求向该等机关提供用户的资料及信息。</p>
                    <p>2.6 用户应当谨慎地保存、使用其平台账号、密码、手机验证码等信息。用户不得将平台账号向他人透露、借用，否则用户应当承担由此产生的全部后果及责任。</p>
                    <p class="title">第三条 平台服务</p>
                    <p>平台依靠互联网依法向平台注册用户提供互联网信息服务等服务内容。</p>
                    <p class="title">第四条 用户承诺</p>
                    <p>4.1 用户应当妥善保管本人的平台账号、密码、绑定的手机号码、手机验证码等信息。对于非因平台过错产生的上述信息泄露所导致的用户损失平台不承担任何责任。</p>
                    <p>4.2 用户承诺在接受平台服务过程中应当诚实、守信地履行相关义务，否则将承担包含但不限于下列后果：</p>
                    <p>(1)用户的不良信用信息将被上传至经中国人民银行批准并依法设立的各征信数据机构；</p>
                    <p>(2)用户将因违约行为承担相应的违约责任；</p>
                    <p>(3)用户将因违约行为承担相应的诉讼后果。</p>
                    <p class="title">第五条 征信授权</p>
                    <p>5.1 用户在此不可撤销地授权平台通过依法设立的征信机构了解、咨询、审查用户的个人信息、信用状况、履约能力及其他评定用户资信状况的信息，包含可能存在的用户不良信用信息。</p>
                    <p>5.2 用户在此不可撤销地授权平台向依法设立的征信机构提供用户接受平台服务所对应的个人信息、借贷信息及后续还款记录等信息。</p>
                    <p>5.3 用户在此不可撤销地授权平台向依法设立的征信机构提供用户可能产生的不良信用信息。</p>
                    <p class="title">第六条 关于电子合同</p>
                    <p>6.1 本协议采用电子文本形式制成，并在平台系统上保留存档。其签订方式符合《中华人民共和国电子签名法》的要求。用户通过平台系统点击确认或以其他方式选择接受本协议，即表示已同意接受本协议的全部内容以及与本协议有关的各项平台规则。</p>
                    <p>6.2 用户应当妥善保管自己的账号、密码等账户信息，不得以账户信息被盗用或其他理由否认已订立的协议的效力或不履行相关义务。</p>
                    <p class="title">第七条 责任限制</p>
                    <p>除非另有明确的书面说明,平台及其所包含的或以其它方式通过平台提供给用户的全部信息、内容、材料、产品(包括软件)和服务，均是在"按现状"和"按现有"的基础上提供的。</p>
                    <p>如因不可抗力或其它平台无法控制的原因平台系统崩溃或无法正常使用导致无法向用户提供平台服务的，平台不承担任何责任。</p>
                    <p class="title">第八条 联系信息更新</p>
                    <p>用户接受平台服务期间，用户本人姓名、身份证号码、手机号码、银行账户等信息如发生变更，应当在相关信息发生变更之日起三日内将更新后的信息提供给平台。因用户未能及时提供上述变更信息而带来的损失或额外费用应由用户自行承担。</p>
                    <p class="title">第九条 适用法律及争议解决</p>
                    <p>9.1 本协议的签订、履行、终止、解释均适用中华人民共和国法律。</p>
                    <p>9.2 因履行本协议所产生的争议应当通过友好协商解决；如协商不成，则本协议任意一方均可向本协议签订地上海市杨浦区有管辖权的人民法院起诉。</p>
                    <p class="title">第十条 其他</p>
                    <p>10.1 本协议未尽事项按照平台现有及不时发布的各项规则执行。</p>
                    <p>10.2 如本协议中的任何一条或多条被确认为无效，该无效条款并不影响本协议其他条款的效力。</p>
                </div>
                <a href="javascript:void(0)" class="btn-iknow" v-on:click="iknow">我知道了</a>
                <p class="xy">点击登录注册即同意<<蜂优贷服务协议>></p>
            </div>
        </div>
    </div>
    <script src="/assets/js/layer_mobile/layer.js" type="text/javascript"></script>
    <script src="/assets/js/zepto.min.js" type="text/javascript"></script>
    <script src="/assets/js/vue.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var vm = new Vue({
            el:'.wrap',
            data:{
                mobile:'',
                code:'',
                countDown:0,
                showCodeBox:false,
                showXyBox:false,
                codeImgUrl:'/code',
                vcode:''
            },
            methods:{
                getQueryString:function(sProp){
                    var re = new RegExp("[&,?]" + sProp + "=([^\\&]*)", "i");
                    var a = re.exec(document.location.search);
                    if (a == null)
                        return "";
                    return a[1];
                },
                back:function(){
                    console.log('go back...');
                },
                getCode:function(){
                    if(!this.mobile){
                        layer.open({content: '请先填写手机号',skin: 'msg',time: 2 });
                        return false;
                    }
                    this.showCodeBox=true;
                    this.vcode='';
                    this.refreshCode();
                },
                agree:function(){
                    this.showXyBox=true;
                },
                go:function(){
                    if(!this.mobile){
                        layer.open({content: '请先填写手机号',skin: 'msg',time: 2 });
                        return false;
                    }
                    if(!this.code){
                        layer.open({content: '请先填写验证码',skin: 'msg',time: 2 });
                        return false;
                    }
                    //提交
                    var key = this.getQueryString('key');
                    $.ajax({
                        url: '/account/registerSubmit',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            mobile: vm.$data.mobile,
                            yzm: vm.$data.code,
                            key: key
                        },
                        success: function (data) {
                            if (data.code != 1) {
                                layer.open({content: data.message, skin: 'msg', time: 2});
                            } else {
                                layer.open({content: '跳转中...', skin: 'msg', time: 2});
                                window.location.reload();
                            }
                        },
                        error: function () {
                            layer.open({content: '网络异常', skin: 'msg', time: 2});
                        }
                    });
                },
                closeCodeBox:function(){
                    this.showCodeBox=false;
                },
                refreshCode:function(){
                    this.codeImgUrl="/code?date=" + Date.parse(new Date());
                },
                confirmCode:function(){
                    var vcode = this.vcode;
                    if(!vcode){
                        layer.open({content: '请先填写验证码',skin: 'msg',time: 2 });
                        return false;
                    }
                    $.ajax({
                        url:'/account/validateVcode',
                        type:'POST',
                        dataType:'json',
                        data:{
                            key:vcode
                        },
                        success:function(data){
                            if(data.code!=1){
                                layer.open({content: data.message,skin: 'msg',time: 2 });
                            }else {
                                //发送短信
                                $.ajax({
                                    url: '/account/getRegisterCode',
                                    type: 'POST',
                                    dataType: 'json',
                                    data: {
                                        mobile: vm.$data.mobile
                                    },
                                    success: function (data) {
                                        if (data.code != 1) {
                                            layer.open({content: data.message, skin: 'msg', time: 2});
                                        } else {
                                            vm.$data.showCodeBox = false;
                                            //倒计时
                                            vm.$data.countDown=60;
                                            var time = setInterval(function(){
                                                vm.$data.countDown--;
                                                if(vm.$data.countDown===0)
                                                    clearInterval(time);
                                            },1000);
                                        }
                                    },
                                    error: function () {
                                        layer.open({content: '网络异常', skin: 'msg', time: 2});
                                    }
                                });
                            }
                        },
                        error:function(){
                            layer.open({content: '网络异常',skin: 'msg',time: 2 });
                        }
                    });
                },
                iknow:function(){
                    this.showXyBox=false;
                }
            }
        })
    </script>
</body>
</html>
