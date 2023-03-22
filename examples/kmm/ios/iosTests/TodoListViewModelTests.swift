import XCTest
@testable import ios
@testable import shared

final class TodoListViewModelTests: XCTestCase {

    override func setUpWithError() throws {
        KojectHelper.shared.startTest()
    }

    override func tearDownWithError() throws {
        KojectHelper.shared.stop()
    }

    func testAddTask() throws {
        let subject = TodoListViewModel()
        subject.addTask(title: "test")
        XCTAssertEqual(subject.list.value.count, 1)
    }
    
    func testChangeComplete() throws {
        let subject = TodoListViewModel()
        subject.addTask(title: "test")

        let task = subject.list.value[0]
        XCTAssertFalse(task.isCompleted)
        
        subject.changeComplete(task: task, isCompleted: true)
        let actual = subject.list.value[0]
        XCTAssertTrue(actual.isCompleted)
    }
}
