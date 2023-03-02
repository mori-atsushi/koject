import SwiftUI
import shared

struct TodoListPage: View {
    @ObservedObject
    private var viewModel: TodoListViewModel = TodoListViewModel()
    
    @State
    private var editingTitle = ""

    var body: some View {
        NavigationView {
            VStack {
                TodoListView(
                    list: viewModel.list,
                    onChangeCompleted: { task, isCompleted in
                        viewModel.changeComplete(task: task, isCompleted: isCompleted)
                    }
                )
                TodoTaskField(
                    title: $editingTitle,
                    onClickAddButton: {
                        viewModel.addTask(title: editingTitle)
                        editingTitle = ""
                    }
                )
            }
            .navigationBarTitle("Koject TODO")
        }
    }
}
