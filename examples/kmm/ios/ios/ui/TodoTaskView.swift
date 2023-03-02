import SwiftUI
import shared

struct TodoTaskView: View {
    let task: TodoTask
    let onChangeCompleted: (Bool) -> Void

    var body: some View {
        HStack(alignment: .center) {
            CheckBox(
                checked: Binding(
                    get: { task.isCompleted },
                    set: { isCompleted in onChangeCompleted(isCompleted) }
                )
            )
            Text(task.title)
                .font(.system(size: 18))
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 4)
    }
}
