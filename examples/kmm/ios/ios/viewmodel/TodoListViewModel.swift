import Combine
import shared

import Foundation

class TodoListViewModel: ObservableObject {
    @Published var tasks: [TodoTask] = []
    
    private let viewModel: IOSTodoListViewModel =
        KojectHelper_iosKt.injectTodoListViewModel()
    
    init() {
        self.viewModel.observeTasks { [unowned self] tasks in
            self.tasks = tasks
        }
    }
    
    func addTask(title: String) {
        self.viewModel.addTask(title: title)
    }
    
    deinit {
        self.viewModel.onCleared()
    }
}
