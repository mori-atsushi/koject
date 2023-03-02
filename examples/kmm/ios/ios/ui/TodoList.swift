import SwiftUI
import shared

struct TodoList: View {
    @ObservedObject
    private var viewModel: TodoListViewModel = TodoListViewModel()
    
    @State
    private var editingTitle = ""

    var body: some View {
        VStack {
            TextField("Title", text: $editingTitle)
                .onSubmit {
                    viewModel.addTask(title: editingTitle)
                }
            List(viewModel.tasks, id: \.title) { task in
                Text(task.title)
            }
        }
    }
}
