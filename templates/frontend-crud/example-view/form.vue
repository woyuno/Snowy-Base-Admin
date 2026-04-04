<template>
	<xn-form-container
		:title="formData.id ? '编辑示例' : '新增示例'"
		:width="550"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical">
			<a-form-item label="示例名称" name="name">
				<a-input v-model:value="formData.name" placeholder="请输入示例名称" allow-clear />
			</a-form-item>
			<a-form-item label="示例编码" name="code">
				<a-input v-model:value="formData.code" placeholder="请输入示例编码" allow-clear />
			</a-form-item>
			<a-form-item label="排序" name="sortCode">
				<a-input-number v-model:value="formData.sortCode" class="xn-wd" :max="999" />
			</a-form-item>
			<a-form-item label="备注" name="remark">
				<a-textarea v-model:value="formData.remark" placeholder="请输入备注" :rows="4" />
			</a-form-item>
		</a-form>
		<template #footer>
			<a-button class="xn-mr8" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="devExampleForm">
	import exampleApi from '@/api/dev/exampleApi'
	import { required } from '@/utils/formRules'

	const emit = defineEmits({ successful: null })
	const visible = ref(false)
	const submitLoading = ref(false)
	const formRef = ref()
	const formData = ref({})

	const formRules = {
		name: [required('请输入示例名称')],
		code: [required('请输入示例编码')],
		sortCode: [required('请输入排序')]
	}

	const onOpen = (record) => {
		visible.value = true
		formData.value = record
			? {
					...record
				}
			: {
					sortCode: 99
				}
	}

	const onClose = () => {
		visible.value = false
		formData.value = {}
	}

	const onSubmit = () => {
		formRef.value
			.validate()
			.then(() => {
				submitLoading.value = true
				exampleApi
					.submitForm(formData.value, !!formData.value.id)
					.then(() => {
						onClose()
						emit('successful')
					})
					.finally(() => {
						submitLoading.value = false
					})
			})
			.catch(() => {})
	}

	defineExpose({
		onOpen
	})
</script>
