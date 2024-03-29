import SwiftUI
import shared

struct TodoListView: View {
    let list: DataTodoList
    let onChangeCompleted: (DataTodoTask, Bool) -> Void
    
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(list.value, id: \.id) { task in
                    TodoTaskView(
                        task: task,
                        onChangeCompleted: { isCompleted in
                            onChangeCompleted(task, isCompleted)
                        }
                    )
                }
            }
            .padding(.vertical, 8)
        }
    }
}
