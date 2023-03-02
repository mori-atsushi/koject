import SwiftUI

struct CheckBox: View {
    @Binding
    var checked: Bool
    
    var body: some View {
        Button(action: toggle) {
            if (checked) {
                Image(systemName: "checkmark.square.fill")
            } else {
                Image(systemName: "square")
            }
        }
        .foregroundColor(.black)
    }
    
    private func toggle() -> Void {
        checked = !checked
    }
}
