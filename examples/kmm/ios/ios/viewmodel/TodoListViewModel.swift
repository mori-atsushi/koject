import Combine
import shared

import Foundation

class TodoListViewModel: ObservableObject {
    @Published
    var list: TodoList = TodoList.Companion().empty
    
    private let viewModel: IOSTodoListViewModel =
        KojectHelper_iosKt.injectTodoListViewModel()
    
    init() {
        self.viewModel.observeTasks { [unowned self] list in
            self.list = list
        }
    }
    
    func addTask(title: String) {
        self.viewModel.addTask(title: title)
    }
    
    func changeComplete(task: TodoTask, isCompleted: Bool) {
        self.viewModel.changeComplete(task: task, isCompleted: isCompleted)
    }
    
    deinit {
        self.viewModel.onCleared()
    }
}
