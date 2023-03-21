import XCTest

final class IosUITests: XCTestCase {
    let app = XCUIApplication()
    
    func testAddTask() {
        app.launchArguments = ["TESTING"]
        app.launch()
        
        let text = "Sample TODO Task"
        let textFields = app.textFields["input"]
        textFields.tap()
        textFields.typeText(text)
        
        let addButton = app.buttons["add"]
        addButton.tap()
        
        let predicate = NSPredicate(format: "label == %@", text)
        let elements = app.staticTexts.containing(predicate)
        XCTAssertEqual(elements.count, 1)
    }
}
