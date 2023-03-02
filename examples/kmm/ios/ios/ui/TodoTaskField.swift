import SwiftUI

struct TodoTaskField: View {
    @Binding
    var title: String
    
    
    @FocusState
    private var focus: Bool
    
    let onClickAddButton: () -> Void
    
    var body: some View {
        VStack {
            Divider()
            HStack(alignment: .center) {
                TextField(
                    "Input title…",
                    text: $title
                )
                .onSubmit {
                    onClickAddButton()
                }
                .focused($focus)

                Button(
                    action: {
                        onClickAddButton()
                        focus = false
                    }
                ) {
                    Text("Add")
                        .fontWeight(.semibold)
                        .frame(height: 42)
                        .padding(.horizontal, 14)
                        .foregroundColor(Color(.white))
                        .background(Color(.black))
                        .cornerRadius(21)
                }
            }
            .padding(.vertical, 4)
            .padding(.horizontal, 16)
        }
    }
}
