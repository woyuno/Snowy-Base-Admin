import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/example/` + url, ...arg)

export default {
	// 获取示例分页
	examplePage(data) {
		return request('page', data, 'get')
	},
	// 获取示例详情
	exampleDetail(data) {
		return request('detail', data, 'get')
	},
	// 提交表单，edit 为 true 时为编辑
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除示例
	exampleDelete(data) {
		return request('delete', data)
	}
}
