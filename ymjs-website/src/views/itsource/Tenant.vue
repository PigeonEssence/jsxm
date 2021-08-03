<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :model="filters" :inline="true">
				<el-form-item>
					<el-input v-model="filters.keywords" placeholder="关键字" ></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getTenants">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" >新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表v-loading="listLoading"-->
		<el-table :data="tenants" v-loading="listLoading" highlight-current-row  style="width: 100%;">
			<!--多选框-->
			<el-table-column type="selection" width="55">
			</el-table-column>
			<!--索引值,为什么不用id,id不序号-->
			<el-table-column type="index" width="60">
			</el-table-column>
			<!--其他都设置值,只有一个不设置值就自动适应了-->
			<el-table-column prop="companyName" label="机构名称">
			</el-table-column>
			<el-table-column prop="companyNum" label="营业执照">
			</el-table-column>
			<el-table-column prop="address" label="公司地址">
			</el-table-column>
			<el-table-column prop="logo" label="LOGO">

			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small"  @click="edit(scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="del(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange"  :page-size="10" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

	</section>
</template>

<script>
	export default {
		data() {
			return {
                listLoading:false,
				//查询对象
				filters:{
					keywords:''
				},
				page:1,//当前页,要传递到后台的
				total:0, //分页总数
			    tenants:[], //当前页数据
			}
		},
		methods: {
            handleCurrentChange(curentPage){
                this.page = curentPage;
                this.getTenants();
			},
            getTenants(){
                //发送Ajax请求后台获取数据  axios
				//添加分页条件及高级查询条件
				let para = {
				    "page":this.page,
					"keyword":this.filters.keywords
				};
				this.listLoading = true; //显示加载圈
				//分页查询
                this.$http.post("/system/tenant/pagelist",para) //$.Post(.....)
                    .then(result=>{
                        this.total = result.data.total;
                        this.tenants = result.data.rows;
                        this.listLoading = false;  //关闭加载圈
                    });
				/* 查询所有
				this.$http.patch("/department",para) //$.Post(.....)
					.then(result=>{
                        console.log(result);
                        console.log(result.data);
                        this.tenants = result.data;
                        this.listLoading = false;  //关闭加载圈
                    });
                    */
			}
		},
		mounted() {
		    this.getTenants()
		}
	}

</script>

<style scoped>

</style>