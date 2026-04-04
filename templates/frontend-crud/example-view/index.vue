<template>
	<xn-panel>
		<a-form ref="searchFormRef" :model="searchFormState">
			<a-row :gutter="10">
				<a-col :xs="24" :sm="8" :md="8" :lg="8" :xl="8">
					<a-form-item label="关键词" name="searchKey">
						<a-input v-model:value="searchFormState.searchKey" placeholder="请输入示例名称关键词" allow-clear />
					</a-form-item>
				</a-col>
				<a-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
					<a-form-item>
						<a-space>
							<a-button type="primary" @click="tableRef.refresh(true)">
								<template #icon>
									<SearchOutlined />
								</template>
								查询
							</a-button>
							<a-button @click="reset">
								<template #icon>
									<RedoOutlined />
								</template>
								重置
							</a-button>
						</a-space>
					</a-form-item>
				</a-col>
			</a-row>
		</a-form>
		<s-table
			ref="tableRef"
			:columns="columns"
			:data="loadData"
			:alert="options.alert.show"
			bordered
			:row-key="(record) => record.id"
			:row-selection="options.rowSelection"
			:scroll="{ x: 'max-content' }"
		>
			<template #operator>
				<a-space>
					<a-button v-if="hasPerm('dev:example:add')" type="primary" @click="formRef.onOpen()">
						<template #icon><PlusOutlined /></template>
						新增示例
					</a-button>
					<xn-batch-button
						v-if="hasPerm('dev:example:delete')"
						buttonName="批量删除"
						icon="DeleteOutlined"
						buttonDanger
						:selectedRowKeys="selectedRowKeys"
						@batchCallBack="deleteBatchExample"
					/>
				</a-space>
			</template>
			<template #bodyCell="{ column, record }">
				<template v-if="column.dataIndex === 'action'">
					<a-space>
						<a v-if="hasPerm('dev:example:edit')" @click="formRef.onOpen(record)">编辑</a>
						<a-divider type="vertical" />
						<a-popconfirm
							v-if="hasPerm('dev:example:delete')"
							title="确定要删除这条示例数据吗？"
							@confirm="deleteExample(record)"
						>
							<a-button type="link" danger size="small">删除</a-button>
						</a-popconfirm>
					</a-space>
				</template>
			</template>
		</s-table>
	</xn-panel>
	<Form ref="formRef" @successful="tableRef.refresh(true)" />
</template>

<script setup name="devExample">
	import { hasPerm } from '@/utils/permission'
	import exampleApi from '@/api/dev/exampleApi'
	import Form from './form.vue'

	const searchFormRef = ref()
	const tableRef = ref()
	const formRef = ref()
	const searchFormState = ref({})
	const selectedRowKeys = ref([])

	const columns = [
		{
			title: '示例名称',
			dataIndex: 'name'
		},
		{
			title: '示例编码',
			dataIndex: 'code'
		},
		{
			title: '排序',
			dataIndex: 'sortCode',
			sorter: true
		},
		{
			title: '备注',
			dataIndex: 'remark'
		},
		{
			title: '操作',
			dataIndex: 'action',
			align: 'center',
			fixed: 'right'
		}
	]

	const options = {
		alert: {
			show: false,
			clear: () => {
				selectedRowKeys.value = []
			}
		},
		rowSelection: {
			onChange: (keys) => {
				selectedRowKeys.value = keys
			}
		}
	}

	const loadData = (parameter) => {
		return exampleApi.examplePage(Object.assign(parameter, searchFormState.value)).then((res) => {
			return res
		})
	}

	const reset = () => {
		searchFormRef.value.resetFields()
		tableRef.value.refresh(true)
	}

	const deleteExample = (record) => {
		exampleApi
			.exampleDelete([
				{
					id: record.id
				}
			])
			.then(() => {
				tableRef.value.refresh(true)
			})
	}

	const deleteBatchExample = (rows) => {
		exampleApi.exampleDelete(rows).then(() => {
			tableRef.value.clearRefreshSelected()
		})
	}
</script>
